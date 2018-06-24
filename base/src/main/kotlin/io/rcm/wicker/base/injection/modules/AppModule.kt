package io.rcm.wicker.base.injection.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.rcm.wicker.base.injection.scopes.PerApplication

/**
 * Module used to provide dependencies at an application-level.
 *
 * Created by joicemarinay on 20/04/2018.
 */
@Module
internal class AppModule {

  @PerApplication
  @Provides
  fun provideAppContext(application: Application): Context = application

}