package com.wallet.service.domain.account.dto

import com.wallet.service.domain.account.data.Account
import org.modelmapper.AbstractConverter

class AccountConverter : AbstractConverter<Account, AccountDto>() {
    override fun convert(source: Account?): AccountDto {
        return AccountDto(source!!.holderName, source.number)
    }
}
