package com.wallet.service.domain.account

import com.wallet.service.domain.account.dto.AccountFullDto
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController(private val accountService: AccountService) {

    @GetMapping
    fun list(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): List<AccountFullDto> {
        return accountService.getAll(PageRequest.of(page, size))
    }

    //TODO post /balance (dto with request to add/remove cash)
}
