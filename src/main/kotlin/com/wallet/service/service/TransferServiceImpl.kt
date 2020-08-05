package com.wallet.service.service

import com.wallet.service.dto.TransferDto
import com.wallet.service.dto.TransferRequest
import com.wallet.service.dto.TransferStatus
import com.wallet.service.exception.AccountNotFoundException
import com.wallet.service.exception.AccountNumberNotExistsException
import com.wallet.service.model.Account
import com.wallet.service.model.Transfer
import com.wallet.service.repository.AccountRepository
import com.wallet.service.repository.TransferRepository
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime.now
import javax.persistence.EntityManager
import javax.persistence.LockModeType
import javax.persistence.PersistenceContext


/**
 * Сервис перевод денежных средств между внутренними счетами
 */
@Service
class TransferServiceImpl(
        private val transferRepo: TransferRepository,
        private val accountsRepo: AccountRepository,
        mapper: ModelMapper
) : WithTransformationService<Transfer, TransferDto, TransferDto>(mapper), TransferService {

    @PersistenceContext
    private lateinit var em: EntityManager

    override fun getAll(pageable: Pageable): List<TransferDto> = toDtoList(transferRepo.findAll(pageable))

    override fun getById(id: Long): TransferDto = toDto(findByTxId(id))

    @Transactional
    override fun transferCash(request: TransferRequest): TransferDto {
        val source = findAccountByNumber(request.source)
        val dest = findAccountByNumber(request.destination)
        val transfer = createTransfer(source, dest, request)

        //TODO catch ConstraintException(tx_id) and return custom HTTP answer?
        transferRepo.save(transfer)

        try {
            em.lock(source, LockModeType.PESSIMISTIC_READ)
            if (source.balance < transfer.cashValue) {
                transfer.status = TransferStatus.REJECTED
                return toDto(transfer)
            }

            source.balance -= transfer.cashValue
            dest.balance += transfer.cashValue

            transfer.status = TransferStatus.DONE
        } catch (e: Exception) {
            transfer.status = TransferStatus.ERROR
        }
        return toDto(transfer)
    }

    private fun createTransfer(source: Account?, dest: Account?, request: TransferRequest) =
            Transfer(source = source, destination = dest,
                    cashValue = request.cashValue, datetime = now(),
                    status = TransferStatus.CREATED, txId = request.txId)

    private fun findByTxId(id: Long) = transferRepo.findById(id)
        .orElseThrow { AccountNotFoundException(id) }

    private fun findAccountByNumber(number: String) =
            accountsRepo.findByNumber(number)
                .orElseThrow { AccountNumberNotExistsException(number) }
}
