package io.rcm.wicker.onboarding.data

import io.rcm.wicker.onboarding.data.preferences.OnboardingPreferences
import javax.inject.Inject

/**
 * Created by joicemarinay on 06/08/2018.
 */
internal class OnboardingRepositoryImpl @Inject constructor(
  private val preferences: OnboardingPreferences): OnboardingRepository {

  override fun isOnboardingComplete(): Boolean = preferences.isOnboardingComplete()

  override fun setOnboardingComplete() {
    preferences.onboardingComplete()
  }
}