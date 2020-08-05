package com.wallet.service.service

import com.wallet.service.dto.AccountDto
import com.wallet.service.dto.AccountFullDto
import com.wallet.service.model.Account
import com.wallet.service.repository.AccountRepository
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(private val repository: AccountRepository, mapper: ModelMapper) : AccountService,
        WithTransformationService<Account, AccountFullDto, AccountDto>(mapper) {

    override fun getAll(pageable: Pageable): List<AccountFullDto> {
        return toDtoList(repository.findAll(pageable))
    }
}