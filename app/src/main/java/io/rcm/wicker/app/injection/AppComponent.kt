package io.rcm.wicker.app.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import io.rcm.wicker.app.WickerApp
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication

/**
 * Created by joicemarinay on 20/04/2018.
 */
@PerApplication
@Component(modules = [
  AndroidSupportInjectionModule::class,
  AppModule::class
])
interface AppComponent {

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(app: WickerApp)

}