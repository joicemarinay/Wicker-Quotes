package io.rcm.wicker.base

import android.app.Application
import io.rcm.wicker.base.injection.components.BaseComponent
import io.rcm.wicker.base.injection.components.DaggerBaseComponent
import timber.log.Timber

/**
 * Created by joicemarinay on 20/04/2018.
 */
//STUDY I've seen other apps make this [open].
class WickerApp: Application() {

  override fun onCreate() {
    super.onCreate()
    initDependencyInjection()
    setupTimber()
  }

  private fun initDependencyInjection() {
    baseComponent = DaggerBaseComponent.builder().application(this).build()
  }

  private fun setupTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  companion object {
    lateinit var baseComponent: BaseComponent
  }
}