package io.rcm.wicker.onboarding.presentation

import io.rcm.wicker.base.presentation.BaseUiState

/**
 * Created by joicemarinay on 02/08/2018.
 */
internal sealed class OnboardingState: BaseUiState() {

  object ExitOnboarding: OnboardingState()
  data class PageChange(val isGetStartedVisible: Boolean): OnboardingState()
  data class PagesLoaded(val pages: List<OnboardingPage>): OnboardingState()
}