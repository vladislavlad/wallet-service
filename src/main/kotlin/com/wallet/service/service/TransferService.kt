package com.wallet.service.service

import com.wallet.service.dto.TransferDto
import com.wallet.service.dto.TransferRequest
import org.springframework.data.domain.Pageable

interface TransferService {

    fun getAll(pageable: Pageable): List<TransferDto>

    fun getById(id: Long): TransferDto

    fun transferCash(request: TransferRequest): TransferDto
}