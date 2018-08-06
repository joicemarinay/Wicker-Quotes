package io.rcm.wicker.onboarding.presentation

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

/**
 * Created by joicemarinay on 05/08/2018.
 */
internal data class OnboardingPage(
  @StringRes val description: Int,
  @DrawableRes val image: Int,
  @StringRes val title: Int
)