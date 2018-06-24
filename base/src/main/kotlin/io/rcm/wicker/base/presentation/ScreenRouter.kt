package io.rcm.wicker.base.presentation

import android.content.Context
import android.content.Intent

/**
 *
 * Interface for navigating between screens
 *
 * All credits go to MojRoid
 *
 * @see https://github.com/MojRoid/memes/blob/master/base/src/main/java/moj/memes/base/view/ScreenRouter.kt
 */
interface ScreenRouter {

  //STUDY why is this sealed?
  sealed class Screen {
    object List: Screen()
    object Write: Screen()
  }

  fun getScreenIntent(context: Context, screen: Screen): Intent?
}