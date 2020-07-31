package com.wallet.service.service

import com.wallet.service.repository.TransferRepository
import org.modelmapper.ModelMapper

class AccountService(private val repository: TransferRepository, mapper: ModelMapper) {
}