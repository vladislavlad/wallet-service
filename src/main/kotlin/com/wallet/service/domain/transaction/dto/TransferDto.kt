package com.wallet.service.domain.transaction.dto

import com.wallet.service.domain.account.dto.AccountDto
import java.time.LocalDateTime

data class TransferDto(
    var txId: String? = null,
    var source: AccountDto? = null,
    var destination: AccountDto? = null,
    var cashValue: Long? = null,
    var datetime: LocalDateTime? = null,
    var status: TransferStatus? = null
)
