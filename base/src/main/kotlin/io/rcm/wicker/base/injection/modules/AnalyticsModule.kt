package io.rcm.wicker.base.injection.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.rcm.wicker.base.analytics.AnalyticsToolImpl
import javax.inject.Singleton

/**
 * Created by joicemarinay on 12/08/2018.
 */
@Module
internal class AnalyticsModule {

    @Provides
    @Singleton
    fun provideAnalyticsTool(context: Context): AnalyticsToolImpl = AnalyticsToolImpl(context)
}