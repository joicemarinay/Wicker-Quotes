package io.rcm.wicker.base.injection.components

import android.content.Context
import dagger.Component
import io.rcm.wicker.base.injection.modules.AppModule
import javax.inject.Singleton

/**
 * Created by joicemarinay on 20/04/2018.
 */
@Component(modules = [
  AppModule::class
])
@Singleton
interface BaseComponent {

  fun context(): Context
}