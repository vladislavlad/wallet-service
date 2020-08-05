package com.wallet.service.service

import com.wallet.service.dto.AccountFullDto
import org.springframework.data.domain.Pageable


interface AccountService {

    fun getAll(pageable: Pageable): List<AccountFullDto>
}