package io.rcm.wicker.base.data

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <T> the cached model input type
 * @param <T> the remote model input type
 * @param <V> the model return type
 *
 * Created by joicemarinay on 20/04/2018.
 */
interface EntityMapper<T, V> {

  fun mapFromLocal(type: T): V

  fun mapFromLocal(types: List<T>): List<V>

  fun mapToLocal(type: V): T

  fun mapToLocal(types: List<V>): List<T>

}