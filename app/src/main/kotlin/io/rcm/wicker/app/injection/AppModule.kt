package io.rcm.wicker.app.injection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.rcm.wicker.app.presentation.RealScreenRouter
import io.rcm.wicker.base.presentation.ScreenRouter
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication

/**
 * Module used to provide dependencies at an application-level.
 *
 * Created by joicemarinay on 20/04/2018.
 */
@Module
internal class AppModule {

  @PerApplication
  @Provides
  fun provideContext(application: Application): Context {
    return application
  }

  @Provides
  @PerApplication
  fun provideScreenRouter(): ScreenRouter {
    return RealScreenRouter()
  }
}