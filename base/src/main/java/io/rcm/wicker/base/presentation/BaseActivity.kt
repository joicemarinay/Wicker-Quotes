package io.rcm.wicker.base.presentation

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection

/**
 * This should be implemented by all Activities instead of [AppCompatActivity].
 * Prevents repetition of injection and setting of layout resource per activity.
 *
 * All credits go to MojRoid
 *
 * @see https://github.com/MojRoid/memes/blob/master/base/src/main/java/moj/memes/base/view/BaseActivity.kt
 */
abstract class BaseActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)
    setContentView(layoutResourceId)
  }

  @get:LayoutRes
  protected abstract val layoutResourceId: Int
}