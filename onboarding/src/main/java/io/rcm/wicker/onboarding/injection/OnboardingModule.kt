package io.rcm.wicker.onboarding.injection

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.rcm.wicker.base.injection.keys.ViewModelKey
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

}