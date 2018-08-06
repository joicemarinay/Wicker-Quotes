package io.rcm.wicker.onboarding.injection

import dagger.Component
import io.rcm.wicker.base.injection.components.BaseComponent
import io.rcm.wicker.onboarding.presentation.OnboardingActivity

/**
 * Created by joicemarinay on 05/08/2018.
 */
@Component(
  dependencies = [BaseComponent::class],
  modules = [OnboardingModule::class]
)
@OnboardingScope
internal interface OnboardingComponent {

  fun inject(onboardingActivity: OnboardingActivity)
}