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
@Module(includes = [
  OnboardingModule.Data::class,
  OnboardingModule.Domain::class,
  OnboardingModule.Presentation::class
])
internal abstract class OnboardingModule {

  @Module
  internal abstract class Data {

    @Binds
    abstract fun preferences(preferences: OnboardingPreferencesImpl): OnboardingPreferences

    @Binds
    abstract fun repository(onboardingRepository: OnboardingRepositoryImpl): OnboardingRepository

  }

  @Module
  internal abstract class Domain {

    @Binds
    abstract fun completeOnboardingUseCase(completeOnboarding: CompleteOnboardingUseCase):
      CompleteOnboarding

    @Binds
    abstract fun showOnboardingUseCase(showOnboarding: ShowOnboardingUseCase): ShowOnboarding

  }

  @Module
  internal abstract class Presentation {

    @Binds
    @IntoMap
    @ViewModelKey(OnboardingViewModel::class)
    abstract fun onboardingViewModel(onboardingViewModel: OnboardingViewModel): ViewModel

  }

}