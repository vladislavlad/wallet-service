package com.wallet.service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class AccountNotFoundException(id: Long) : RuntimeException("Account with id = $id not found")
