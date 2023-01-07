package com.wallet.service.domain.transaction.data

import com.wallet.service.domain.account.data.Account
import com.wallet.service.domain.transaction.dto.TransferStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "transfer")
data class Transfer(
    @ManyToOne
    @JoinColumn(name = "source_id")
    var source: Account? = null,
    @ManyToOne
    @JoinColumn(name = "destination_id")
    var destination: Account? = null,
    @Column(name = "cash_value")
    var cashValue: Long,
    var datetime: LocalDateTime,
    @Enumerated(EnumType.STRING)
    var status: TransferStatus,
    @Column(name = "tx_id")
    var txId: Long,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)
