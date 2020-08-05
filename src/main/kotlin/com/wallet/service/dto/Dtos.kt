package com.wallet.service.dto

import com.wallet.service.model.Account
import org.modelmapper.AbstractConverter
import java.time.LocalDateTime
import javax.validation.constraints.Positive

/**
 * DTO передачи запроса на перевод денежных средств
 */
data class TransferRequest(
        @get:AccountNumber var source: String,
        @get:AccountNumber var destination: String,
        @get:Positive var cashValue: Long,
        var txId: Long
)

data class TransferDto(
        var txId: String? = null,
        var source: AccountDto? = null,
        var destination: AccountDto? = null,
        var cashValue: Long? = null,
        var datetime: LocalDateTime? = null,
        var status: TransferStatus? = null
)

data class AccountFullDto(
        var holderName: String,
        var number: String,
        var balance: Long
)

data class AccountDto(
        var holderName: String,
        var number: String
)

//Converters
class AccountConverter : AbstractConverter<Account, AccountDto>() {
    override fun convert(source: Account?): AccountDto {
        return AccountDto(source!!.holderName, source.number)
    }
}
class AccountFullConverter : AbstractConverter<Account, AccountFullDto>() {
    override fun convert(source: Account?): AccountFullDto {
        return AccountFullDto(source!!.holderName, source.number, source.balance)
    }
}

enum class TransferStatus {
    DONE,
    CREATED,
    REJECTED,
    ERROR
}
