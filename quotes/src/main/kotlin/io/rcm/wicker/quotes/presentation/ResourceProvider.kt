package io.rcm.wicker.quotes.presentation

/**
 * Created by joicemarinay on 7/7/18.
 */
internal interface ResourceProvider {

  fun appName(): String

  fun enDash(): String

  fun closeQuoteMark(): String

  fun openQuoteMark(): String
}