package io.rcm.wicker.settings.presentation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.rcm.wicker.settings.R
import kotlinx.android.synthetic.main.wicker_webview.*

/**
 * Created by joicemarinay on 08/08/2018.
 */
internal class WebViewActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.wicker_webview)
    intent?.let {
      credits_webview.loadUrl(htmlAssetToLoad(it))
      title = toolbarTitle(it)
    }
  }

  private fun htmlAssetToLoad(intent: Intent): String =
    "file:///android_asset/${intent.extras.getString(getString(R.string.webview_extra_html_asset))}"

  private fun toolbarTitle(intent: Intent): String =
    intent.extras.getString(getString(R.string.webview_extra_toolbar_title))

}