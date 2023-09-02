package com.wallet.service.domain.account

import com.wallet.service.domain.account.dto.AccountFullDto
import org.springframework.data.domain.Pageable

interface AccountService {

    fun getAll(pageable: Pageable): List<AccountFullDto>
}
