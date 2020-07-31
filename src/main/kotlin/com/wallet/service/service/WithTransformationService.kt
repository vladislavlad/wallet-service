package com.wallet.service.service

import org.modelmapper.ModelMapper

/**
 * Добавляет поддержку трансформации из entity в dto и обратно
 */
abstract class WithTransformationService<E, D, U>(val mapper: ModelMapper) {

    inline fun <reified E> toEntity(dto: U): E = mapper.map(dto, E::class.java)

    inline fun <reified D> toDto(entity: E): D = mapper.map(entity, D::class.java)

    inline fun <reified D> toDtoList(entities: Iterable<E>): List<D> = entities.map { toDto<D>(it) }

    fun updateFields(updateDto: U, entity: E) = mapper.map(updateDto, entity)
}