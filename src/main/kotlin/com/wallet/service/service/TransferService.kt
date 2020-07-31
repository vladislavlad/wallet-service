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
class TransferService(
        private val transferRepo: TransferRepository,
        private val accountsRepo: AccountRepository,
        mapper: ModelMapper
) : WithTransformationService<Transfer, TransferDto, TransferDto>(mapper) {

    @PersistenceContext
    private lateinit var em: EntityManager

    fun getAll(pageable: Pageable): List<TransferDto> = toDtoList(transferRepo.findAll(pageable))

    fun getById(id: Long): TransferDto = toDto(findByIdOrException(id))

    @Transactional
    fun transferCash(request: TransferRequest): TransferDto {
        val source = accountsRepo.findByNumber(request.source).orElseThrow { AccountNumberNotExistsException(request.source) }
        val dest = accountsRepo.findByNumber(request.destination).orElseThrow { AccountNumberNotExistsException(request.destination) }

        val transfer = startTransfer(source, dest, request)
        transferRepo.save(transfer)

        em.lock(source, LockModeType.PESSIMISTIC_WRITE)
        if (source.balance < transfer.cashValue) {
            transfer.status = TransferStatus.REJECTED
            return toDto(transfer)
        }

        source.balance -= transfer.cashValue
        dest.balance += transfer.cashValue

        transfer.status = TransferStatus.DONE
        return toDto(transfer)
    }

    private fun startTransfer(source: Account?, dest: Account?, request: TransferRequest) =
            Transfer(source = source, destination = dest, cashValue = request.cashValue, datetime = now(), status = TransferStatus.CREATED)

    private fun findByIdOrException(id: Long) = transferRepo.findById(id).orElseThrow { AccountNotFoundException(id) }
}
