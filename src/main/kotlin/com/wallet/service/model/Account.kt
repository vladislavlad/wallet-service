package com.wallet.service.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Version

@Entity
@Table(name = "accounts", schema = "wallet")
data class Account(
        @Column(name = "holder_name")
        var holderName: String,
        var number: String,
        var balance: Long,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Version
        var version: Long? = null
)