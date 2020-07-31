package com.wallet.service.model

import com.wallet.service.dto.TransferStatus
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.Version


@Entity
@Table(name = "transfer", schema = "wallet")
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
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null
)
