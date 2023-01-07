package com.wallet.service.domain.transaction.dto

import com.wallet.service.domain.account.dto.AccountNumber
import jakarta.validation.constraints.Positive

data class TransferRequestDto(
    @get:AccountNumber var source: String,
    @get:AccountNumber var destination: String,
    @get:Positive var cashValue: Long,
    var txId: Long
)
