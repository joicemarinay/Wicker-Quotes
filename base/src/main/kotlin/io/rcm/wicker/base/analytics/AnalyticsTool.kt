package io.rcm.wicker.base.analytics

import android.app.Activity

/**
 * Created by joicemarinay on 12/08/2018.
 */
interface AnalyticsTool {

  fun sendEvent(feature: AnalyticsEvent.Feature, event: String)

  fun setScreen(activity: Activity, screenName: String)
}