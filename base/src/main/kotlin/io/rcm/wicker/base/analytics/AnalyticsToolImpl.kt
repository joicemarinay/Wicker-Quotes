package io.rcm.wicker.base.analytics

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.crashlytics.android.answers.FirebaseAnalyticsEvent
import com.google.firebase.analytics.FirebaseAnalytics
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by joicemarinay on 11/08/2018.
 */
internal class AnalyticsToolImpl(context: Context): AnalyticsTool {

  private val firebase: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

  override fun sendEvent(feature: AnalyticsEvent.Feature, event: String) {
    val bundle = Bundle()
    bundle.putString("feature", feature.name)
    firebase.logEvent(event, bundle)
  }

  override fun setScreen(activity: Activity, screenName: String) {
    Timber.d("setScreen() $activity $screenName")
    val params = Bundle()
    params.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "screen")
    params.putString(FirebaseAnalytics.Param.ITEM_NAME, "MainActivity")
    firebase.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, params)
  }
}