package com.wallet.service.domain.transaction

import com.wallet.service.domain.account.data.Account
import com.wallet.service.domain.account.data.AccountRepository
import com.wallet.service.domain.account.exception.AccountNotFoundException
import com.wallet.service.domain.account.exception.AccountNumberNotExistsException
import com.wallet.service.domain.common.WithTransformationService
import com.wallet.service.domain.transaction.data.Transfer
import com.wallet.service.domain.transaction.data.TransferRepository
import com.wallet.service.domain.transaction.dto.TransferDto
import com.wallet.service.domain.transaction.dto.TransferRequestDto
import com.wallet.service.domain.transaction.dto.TransferStatus
import jakarta.persistence.EntityManager
import jakarta.persistence.LockModeType
import jakarta.persistence.PersistenceContext
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime.now

@Service
class TransferServiceImpl(
    private val transferRepo: TransferRepository,
    private val accountsRepo: AccountRepository,
    mapper: ModelMapper
) : WithTransformationService<Transfer, TransferDto, TransferDto>(mapper), TransferService {

    @PersistenceContext
    private lateinit var em: EntityManager

    override fun getAll(pageable: Pageable): List<TransferDto> = transferRepo.findAll(pageable).toDtoList()

    override fun getById(id: Long): TransferDto = findByTxId(id).toDto()

    @Transactional
    override fun transferCash(request: TransferRequestDto): TransferDto {
        val source = findAccountByNumber(request.source)
        val dest = findAccountByNumber(request.destination)
        val transfer = createTransfer(source, dest, request)

        //TODO catch ConstraintException(tx_id) and return custom HTTP answer?
        transferRepo.save(transfer)

        try {
            em.lock(source, LockModeType.PESSIMISTIC_READ)
            if (source.balance < transfer.cashValue) {
                transfer.status = TransferStatus.REJECTED
                return transfer.toDto()
            }

            source.balance -= transfer.cashValue
            dest.balance += transfer.cashValue

            transfer.status = TransferStatus.DONE
        } catch (e: Exception) {
            transfer.status = TransferStatus.ERROR
        }
        return transfer.toDto()
    }

    private fun createTransfer(source: Account?, dest: Account?, request: TransferRequestDto) =
        Transfer(
            source = source, destination = dest,
            cashValue = request.cashValue, datetime = now(),
            status = TransferStatus.CREATED, txId = request.txId
        )

    private fun findByTxId(id: Long) = transferRepo.findById(id)
        .orElseThrow { AccountNotFoundException(id) }

    private fun findAccountByNumber(number: String) =
        accountsRepo.findByNumber(number)
            .orElseThrow { AccountNumberNotExistsException(number) }
}
