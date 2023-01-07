package com.wallet.service.domain.common

import org.modelmapper.ModelMapper

abstract class WithTransformationService<E, D, U>(val mapper: ModelMapper) {

    inline fun <reified E> U.toEntity(): E = mapper.map(this, E::class.java)

    inline fun <reified D> E.toDto(): D = mapper.map(this, D::class.java)

    inline fun <reified D> Iterable<E>.toDtoList(): List<D> = this.map { it.toDto() }

    fun updateFields(updateDto: U, entity: E) = mapper.map(updateDto, entity)
}
