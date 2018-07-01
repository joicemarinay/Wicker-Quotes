package io.rcm.wicker.base.domain

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <T> data/presentation layer model type
 * @param <V> domain layer model type
 *
 * Created by joicemarinay on 20/04/2018.
 */
interface EntityMapper<T, V> {

  fun mapFromDomain(type: V): T

  fun mapFromDomain(types: List<V>): List<T>

  fun mapToDomain(type: T): V

  fun mapToDomain(types: List<T>): List<V>

}