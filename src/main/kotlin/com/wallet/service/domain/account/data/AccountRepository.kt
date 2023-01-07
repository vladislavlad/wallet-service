package com.wallet.service.domain.account.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AccountRepository : JpaRepository<Account, Long> {

    fun findByNumber(number: String): Optional<Account>
}
