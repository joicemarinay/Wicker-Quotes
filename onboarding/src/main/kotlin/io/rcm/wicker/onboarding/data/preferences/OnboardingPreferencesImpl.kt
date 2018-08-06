package io.rcm.wicker.onboarding.data.preferences

import android.content.Context
import android.content.SharedPreferences
import io.rcm.wicker.base.common.SHARED_PREFS_NAME
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by joicemarinay on 06/08/2018.
 */
internal class OnboardingPreferencesImpl @Inject constructor(context: Context):
  OnboardingPreferences {

  /**
   * SharedPreferences mode is set to MODE_PRIVATE so it is only visible to the application.
   */
  private val sharedPrefs: SharedPreferences by lazy {
    context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
  }

  private val editor: SharedPreferences.Editor by lazy {
    sharedPrefs.edit()
  }

  override fun isOnboardingComplete(): Boolean =
    sharedPrefs.getBoolean(KEY_ONBOARDING_COMPLETE, false)

  override fun onboardingComplete() {
    editor.putBoolean(KEY_ONBOARDING_COMPLETE, true).commit()
  }

  companion object {
    private const val KEY_ONBOARDING_COMPLETE = "onboarding_complete"
  }
}