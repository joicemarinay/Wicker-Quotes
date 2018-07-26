package io.rcm.wicker.base.common

/**
 * Created by joicemarinay on 23/07/2018.
 */

/**
 * Source: https://discuss.kotlinlang.org/t/let-vs-if-not-null/3542/5
 */
inline fun <T:Any, R> whenNotNull(input: T?, callback: (T)->R): R? {
  return input?.let(callback)
}