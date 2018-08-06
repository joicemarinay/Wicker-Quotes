package io.rcm.wicker.onboarding.injection

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.rcm.wicker.base.injection.keys.ViewModelKey
import io.rcm.wicker.onboarding.data.OnboardingRepository
import io.rcm.wicker.onboarding.data.OnboardingRepositoryImpl
import io.rcm.wicker.onboarding.data.preferences.OnboardingPreferences
import io.rcm.wicker.onboarding.data.preferences.OnboardingPreferencesImpl
import io.rcm.wicker.onboarding.domain.usecase.CompleteOnboarding
import io.rcm.wicker.onboarding.domain.usecase.CompleteOnboardingUseCase
import io.rcm.wicker.onboarding.domain.usecase.ShowOnboarding
import io.rcm.wicker.onboarding.domain.usecase.ShowOnboardingUseCase
import io.rcm.wicker.onboarding.presentation.OnboardingViewModel

/**
 * Created by joicemarinay on 05/08/2018.
 */
@Module
internal abstract class OnboardingModule {

  @Binds
  @IntoMap
  @ViewModelKey(OnboardingViewModel::class)
  abstract fun bindOnboardingViewModel(onboardingViewModel: OnboardingViewModel): ViewModel

  @Binds
  abstract fun completeOnboardingUseCase(completeOnboarding: CompleteOnboardingUseCase):
    CompleteOnboarding

  @Binds
  abstract fun showOnboardingUseCase(showOnboarding: ShowOnboardingUseCase): ShowOnboarding

  @Binds
  abstract fun preferences(preferences: OnboardingPreferencesImpl): OnboardingPreferences

  @Binds
  abstract fun repository(onboardingRepository: OnboardingRepositoryImpl): OnboardingRepository

}