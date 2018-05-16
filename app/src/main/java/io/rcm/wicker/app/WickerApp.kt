package io.rcm.wicker.app

import android.app.Application
import io.rcm.wicker.app.injection.AppComponent
import io.rcm.wicker.app.injection.DaggerAppComponent

/**
 * Created by joicemarinay on 20/04/2018.
 */
//STUDY I've seen other apps make this [open].
class WickerApp: Application() {

  private val component: AppComponent by lazy {
    DaggerAppComponent.builder().application(this).build()
  }

  override fun onCreate() {
    super.onCreate()
    //Initialize injection
    component.inject(this)
  }
}