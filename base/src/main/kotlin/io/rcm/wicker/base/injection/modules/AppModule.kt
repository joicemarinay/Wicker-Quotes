package io.rcm.wicker.base.injection.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module used to provide dependencies at an application-level.
 *
 * Created by joicemarinay on 20/04/2018.
 */
@Module
internal class AppModule(private val appContext: Context) {

  @Provides
  @Singleton
  fun appContext(): Context = appContext

}