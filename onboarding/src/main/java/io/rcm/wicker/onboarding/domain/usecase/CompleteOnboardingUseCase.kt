package io.rcm.wicker.onboarding.domain.usecase

import io.rcm.wicker.base.domain.BaseUseCase
import io.rcm.wicker.onboarding.data.OnboardingRepository
import javax.inject.Inject

/**
 * Created by joicemarinay on 06/08/2018.
 */
internal class CompleteOnboardingUseCase @Inject constructor(private val repo: OnboardingRepository):
  BaseUseCase<CompleteOnboarding.Result>(), CompleteOnboarding {

  override fun execute() {
    repo.setOnboardingComplete()
  }
}