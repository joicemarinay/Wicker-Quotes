package io.rcm.wicker.onboarding.domain.usecase

import io.rcm.wicker.base.domain.BaseUseCase
import io.rcm.wicker.onboarding.data.OnboardingRepository
import javax.inject.Inject

/**
 * Created by joicemarinay on 06/08/2018.
 */
internal class ShowOnboardingUseCase @Inject constructor(private val repo: OnboardingRepository):
  BaseUseCase<ShowOnboarding.Result>(), ShowOnboarding {

  override fun execute() {
    liveData.value = if(repo.isOnboardingComplete()) {
      ShowOnboarding.Result.Skip
    } else {
      ShowOnboarding.Result.Show
    }
  }
}