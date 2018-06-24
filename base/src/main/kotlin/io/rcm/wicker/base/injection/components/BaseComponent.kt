package io.rcm.wicker.base.injection.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import io.rcm.wicker.base.WickerApp
import io.rcm.wicker.base.injection.modules.AppModule
import io.rcm.wicker.base.injection.scopes.PerApplication

/**
 * Created by joicemarinay on 20/04/2018.
 */
@PerApplication
@Component(modules = [
  AppModule::class
])
interface BaseComponent {

  @Component.Builder
  interface Builder {

    fun build(): BaseComponent

    @BindsInstance
    fun application(application: WickerApp): Builder
  }
}