package io.rcm.wicker.onboarding.injection

import io.rcm.wicker.base.WickerApp
import javax.inject.Singleton

/**
 * Created by joicemarinay on 02/08/2018.
 *
 * Adapted from:
 * https://github.com/karntrehan/Posts/blob/master/posts/src/main/java/com/karntrehan/posts/commons/PostDH.kt
 */
@Singleton
internal object OnboardingDependencyHolder {

  private var onboardingComponent: OnboardingComponent? = null
  private var onboardingModuleCompanion: OnboardingModule.Presentation.Companion? = null
  
  fun onboardingComponent(): OnboardingComponent {
    if (onboardingComponent == null)
      onboardingComponent = DaggerOnboardingComponent.builder()
        .baseComponent(WickerApp.baseComponent)
        .companion(onboardingModuleCompanion()).build()
    return onboardingComponent as OnboardingComponent
  }

  fun destroyOnboardingComponent() {
    onboardingComponent = null
    onboardingModuleCompanion = null
  }

  private fun onboardingModuleCompanion(): OnboardingModule.Presentation.Companion? {
    if (onboardingModuleCompanion == null) {
      onboardingModuleCompanion = OnboardingModule.Presentation.Companion
    }
    return onboardingModuleCompanion
  }


}