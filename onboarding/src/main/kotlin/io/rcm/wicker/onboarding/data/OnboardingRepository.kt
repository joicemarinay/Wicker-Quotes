package io.rcm.wicker.onboarding.data

/**
 * Created by joicemarinay on 06/08/2018.
 */
internal interface OnboardingRepository {

  fun isOnboardingComplete(): Boolean

  fun setOnboardingComplete()
}