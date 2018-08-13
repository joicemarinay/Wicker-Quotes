package io.rcm.wicker.base.injection.modules

import android.app.Activity
import dagger.Module
import dagger.Provides
import io.rcm.wicker.base.injection.scopes.PerActivity

@Module
class ActivityModule constructor(private val activity: Activity) {

  @Provides
  @PerActivity
  fun activity(): Activity = activity
}