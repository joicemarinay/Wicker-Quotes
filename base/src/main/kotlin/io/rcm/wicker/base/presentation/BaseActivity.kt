package io.rcm.wicker.base.presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

/**
 * This should be implemented by all Activities instead of [AppCompatActivity].
 * Prevents repetition of certain actions such as setting of layout resource
 */
abstract class BaseActivity<T: ViewModel>: AppCompatActivity() {

  @Inject
  protected lateinit var viewModel: T

  @Inject
  protected lateinit var viewModelFactory: ViewModelFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModel.javaClass)
    setContentView(layoutResourceId)
  }

  @get:LayoutRes
  protected abstract val layoutResourceId: Int
}