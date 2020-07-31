package com.wallet.service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class InsufficientFundsException(accountNumber: String)
    : RuntimeException("Insufficient funds on account $accountNumber")