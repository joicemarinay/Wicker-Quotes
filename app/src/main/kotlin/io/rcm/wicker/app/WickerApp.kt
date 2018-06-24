package io.rcm.wicker.app

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.rcm.wicker.app.injection.AppComponent
import io.rcm.wicker.app.injection.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by joicemarinay on 20/04/2018.
 */
//STUDY I've seen other apps make this [open].
class WickerApp: Application(), HasActivityInjector {

  @Inject
  lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  private val component: AppComponent by lazy {
    DaggerAppComponent.builder().application(this).build()
  }

  init {
    component.inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> {
    return activityDispatchingAndroidInjector
  }

}