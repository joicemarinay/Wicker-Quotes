package io.rcm.wicker.quotes.writer.presentation

import android.os.Bundle
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.R
import io.rcm.wicker.quotes.writer.injection.QuoteWriterComponent

/**
 * Created by joicemarinay on 09/05/2018.
 *
 * This Activity is not internal because it is used by
 * [io.rcm.wicker.app.view.ScreenRouterImpl]
 */
internal class QuoteWriterActivity(override val layoutResourceId: Int = R.layout.wicker_quote_writer_view):
    BaseActivity<QuoteWriterViewModel>() {

  private val component: QuoteWriterComponent by lazy { QuotesDependencyHolder.writerComponent() }

  override fun onCreate(savedInstanceState: Bundle?) {
    component.inject(this)
    super.onCreate(savedInstanceState)
  }
}