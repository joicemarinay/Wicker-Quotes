package io.rcm.wicker.quotes.presentation

import android.content.ClipboardManager
import android.content.Context
import io.rcm.wicker.quotes.R
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by joicemarinay on 04/07/2018.
 *
 * Can only be used in presentation layer.
 *
 * This is useful in cases wherein ViewModel needs to reference a value already declared as a resource.
 *
 * TODO study how only presentation layer can access this via dependency graph
 */
internal class ResourceProviderImpl @Inject constructor(private val context: Context): ResourceProvider {

  override fun appName(): String = "Wicker"

  override fun enDash(): String = context.resources.getString(R.string.char_en_dash)

  override fun closeQuoteMark(): String = context.resources.getString(R.string.char_quotation_mark_close)

  override fun openQuoteMark(): String = context.resources.getString(R.string.char_quotation_mark_open)
}