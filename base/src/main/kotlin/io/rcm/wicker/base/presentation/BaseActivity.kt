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
abstract class BaseActivity<T: ViewModel, U: BaseUiState>: AppCompatActivity() {

  @get:LayoutRes
  protected abstract val layoutResourceId: Int

  @Inject
  protected lateinit var viewModel: T

  @Inject
  protected lateinit var viewModelFactory: ViewModelFactory

  protected abstract fun onStateChange(state: U)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModel.javaClass)
    setContentView(layoutResourceId)
    setToolbar()
  }

  private fun setToolbar() {
    supportActionBar?.setDisplayShowTitleEnabled(false)
  }
}