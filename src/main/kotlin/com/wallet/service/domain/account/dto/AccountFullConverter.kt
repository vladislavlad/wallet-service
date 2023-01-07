package com.wallet.service.domain.account.dto

import com.wallet.service.domain.account.data.Account
import org.modelmapper.AbstractConverter

class AccountFullConverter : AbstractConverter<Account, AccountFullDto>() {
    override fun convert(source: Account?): AccountFullDto {
        return AccountFullDto(source!!.holderName, source.number, source.balance)
    }
}
