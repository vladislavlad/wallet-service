package com.wallet.service.domain.transaction

import com.wallet.service.domain.transaction.dto.TransferDto
import com.wallet.service.domain.transaction.dto.TransferRequestDto
import org.springframework.data.domain.Pageable

interface TransferService {

    fun getAll(pageable: Pageable): List<TransferDto>

    fun getById(id: Long): TransferDto

    fun transferCash(request: TransferRequestDto): TransferDto
}
