package com.wallet.service.repository

import com.wallet.service.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account, Long> {
    fun findByNumber(number: String) : Optional<Account>
}