package io.rcm.wicker.base.presentation

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

/**
 * This should be implemented by all Activities instead of [AppCompatActivity].
 * Prevents repetition of certain actions such as setting of layout resource
 */
abstract class BaseActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layoutResourceId)
  }

  @get:LayoutRes
  protected abstract val layoutResourceId: Int
}