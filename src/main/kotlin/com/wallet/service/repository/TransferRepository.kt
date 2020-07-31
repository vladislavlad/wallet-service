package com.wallet.service.repository

import com.wallet.service.model.Transfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransferRepository : JpaRepository<Transfer, Long>
