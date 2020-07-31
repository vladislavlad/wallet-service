package com.wallet.service.dto

import java.time.LocalDateTime
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

/**
 * DTO передачи запроса на перевод денежных средств
 */
data class TransferRequest(
        @get:AccountNumber var source: String,
        @get:AccountNumber var destination: String,
        @get:Positive var cashValue: Long
)


data class TransferDto(
        var id: Long? = null,
        var source: String? = null,
        var destination: String? = null,
        var cashValue: Long? = null,
        var datetime: LocalDateTime? = null,
        var status: TransferStatus? = null
)

enum class TransferStatus {
    DONE,
    CREATED,
    REJECTED
}
