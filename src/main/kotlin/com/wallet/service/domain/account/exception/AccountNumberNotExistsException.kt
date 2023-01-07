package com.wallet.service.domain.account.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class AccountNumberNotExistsException(number: String) : RuntimeException("Account with number = $number not exists")