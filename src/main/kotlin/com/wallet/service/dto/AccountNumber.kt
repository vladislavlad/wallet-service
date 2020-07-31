package com.wallet.service.dto

import javax.validation.Constraint
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
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
