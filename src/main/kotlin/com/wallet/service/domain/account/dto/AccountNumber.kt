package com.wallet.service.domain.account.dto

import jakarta.validation.Constraint
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

/**
 * Номер счета должен содержать от 1 до 20 цифр
 */
@MustBeDocumented
@Constraint(validatedBy = [])
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@NotNull
@Pattern(regexp = "^\\d{1,20}$")
annotation class AccountNumber(
    val message: String = "Invalid account number",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
