package io.rcm.wicker.app.view

import android.content.Context
import android.content.Intent
import io.rcm.wicker.base.presentation.ScreenRouter
import io.rcm.wicker.writequote.presentation.WriteQuoteActivity

/**
 * All credits go to MojRoid
 *
 * @see https://github.com/MojRoid/memes/blob/master/app-installed/src/main/java/moj/memes/app/view/ScreenRouterImpl.kt
 */
internal class ScreenRouterImpl: ScreenRouter {

  override fun getScreenIntent(context: Context, screen: ScreenRouter.Screen): Intent? {
    val c: Class<*>? = when (screen) {
      ScreenRouter.Screen.List -> null //TODO
      ScreenRouter.Screen.Write -> WriteQuoteActivity::class.java
    }
    return if (c == null) null else Intent(context, c)
  }
}
