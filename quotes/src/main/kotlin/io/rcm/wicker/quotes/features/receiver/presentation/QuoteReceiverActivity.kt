package io.rcm.wicker.quotes.features.receiver.presentation

import android.content.Intent
import android.os.Bundle
import io.rcm.wicker.base.common.showToast
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.R
import io.rcm.wicker.quotes.features.receiver.injection.QuoteReceiverComponent
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterState
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterViewModel
import timber.log.Timber

/**
 * Created by joicemarinay on 22/07/2018.
 */
internal class QuoteReceiverActivity(override val layoutResourceId: Int? = null):
  BaseActivity<QuoteWriterViewModel, QuoteWriterState>() {

  private val component: QuoteReceiverComponent by lazy { QuotesDependencyHolder.receiverComponent() }

  override fun onCreate(savedInstanceState: Bundle?) {
    component.inject(this)
    super.onCreate(savedInstanceState)
    handleIntent()
  }

  override fun onDestroy() {
    QuotesDependencyHolder.destroyReceiverComponent()
    super.onDestroy()
  }

  override fun onStateChange(state: QuoteWriterState) {
    Timber.d("onStateChange() $state")
  }

  private fun handleIntent() {
    val type = intent.type
    if (intent.action == Intent.ACTION_SEND && type != null) {
      if (type == "text/plain") {
        handleIntentSendText(intent) // Handle text being sent
      }
    } else {
      //TODO Handle other intents, such as being started from the home screen
    }
  }

  /**
   * onSaveSuccessful(), specifically finish(), is called here instead of in onStateChange()
   *  when state is [QuoteWriterState.SaveSuccessful] because
   *  finish() needs to be called prior to onResume() completing
   */
  private fun handleIntentSendText(intent: Intent) {
    val extraText = intent.getStringExtra(Intent.EXTRA_TEXT)
    if (extraText != null) {
      viewModel.saveQuote(quote = extraText)
      onSaveSuccessful()
    }
  }

  private fun onSaveSuccessful() {
    showToast(R.string.spiel_saved_to_wicker)
    finish()
  }
}