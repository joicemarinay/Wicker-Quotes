package io.rcm.wicker.onboarding.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.onboarding.injection.OnboardingDependencyHolder
import javax.inject.Inject

/**
 * Created by joicemarinay on 02/08/2018.
 */
internal class OnboardingViewModel @Inject constructor(): BaseViewModel<OnboardingState>() {

  private val uiState: MediatorLiveData<OnboardingState> = MediatorLiveData()

  override fun onCleared() {
    OnboardingDependencyHolder.destroyOnboardingComponent()
    super.onCleared()
  }

  override fun state(): LiveData<OnboardingState> = uiState
}