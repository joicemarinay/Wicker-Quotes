package io.rcm.wicker.base

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import io.fabric.sdk.android.Fabric
import io.rcm.wicker.base.injection.components.BaseComponent
import io.rcm.wicker.base.injection.components.DaggerBaseComponent
import io.rcm.wicker.base.injection.modules.AppModule
import timber.log.Timber

/**
 * Created by joicemarinay on 20/04/2018.
 */
//STUDY I've seen other apps make this [open].
class WickerApp: Application() {

  override fun onCreate() {
    super.onCreate()
    initDependencyInjection()
    setupAnalytics()
    setupTimber()
  }

  private fun crashlytics(): Crashlytics =
    Crashlytics.Builder().core(
      CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build()

  private fun initDependencyInjection() {
    baseComponent = DaggerBaseComponent.builder().appModule(AppModule(this)).build()
  }

  private fun setupAnalytics() {
    Fabric.with(Fabric.Builder(this)
      .kits(crashlytics())
      .debuggable(BuildConfig.DEBUG)
      .build())
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