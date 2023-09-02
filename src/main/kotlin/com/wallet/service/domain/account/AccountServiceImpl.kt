package com.wallet.service.domain.account

import com.wallet.service.domain.account.data.Account
import com.wallet.service.domain.account.data.AccountRepository
import com.wallet.service.domain.account.dto.AccountDto
import com.wallet.service.domain.account.dto.AccountFullDto
import com.wallet.service.domain.common.WithTransformationService
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    private val repository: AccountRepository,
    mapper: ModelMapper
) : AccountService, WithTransformationService<Account, AccountFullDto, AccountDto>(mapper) {

    override fun getAll(pageable: Pageable): List<AccountFullDto> {
        return repository.findAll(pageable).toDtoList()
    }
}
