package io.rcm.wicker.onboarding.data.preferences

/**
 * Created by joicemarinay on 06/08/2018.
 */
interface OnboardingPreferences {

  fun isOnboardingComplete(): Boolean

  fun onboardingComplete()
}