package com.wallet.service.domain.account.dto

data class AccountFullDto(
    var holderName: String,
    var number: String,
    var balance: Long
)
