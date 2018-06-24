package io.rcm.wicker.quotes.writer.presentation

import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.quotes.R

/**
 * Created by joicemarinay on 09/05/2018.
 *
 * This Activity is not internal because it is used by
 * [io.rcm.wicker.app.view.ScreenRouterImpl]
 */
class QuoteWriterActivity(override val layoutResourceId: Int = R.layout.wicker_quote_writer_view):
    BaseActivity()