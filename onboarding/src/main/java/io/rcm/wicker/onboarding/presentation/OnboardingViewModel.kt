package io.rcm.wicker.onboarding.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.onboarding.R
import io.rcm.wicker.onboarding.injection.OnboardingDependencyHolder
import javax.inject.Inject

/**
 * Created by joicemarinay on 02/08/2018.
 */
internal class OnboardingViewModel @Inject constructor(): BaseViewModel<OnboardingState>() {

  private val pageBeta: OnboardingPage by lazy {
    OnboardingPage(description = R.string.spiel_onboarding_beta,
      image = R.drawable.onboarding_beta,
      title = R.string.title_onboarding_beta)
  }

  private val pageOverview: OnboardingPage by lazy {
    OnboardingPage(description = R.string.spiel_onboarding_overview,
      image = R.drawable.onboarding_overview,
      title = R.string.title_onboarding_overview)
  }

  private val pageShare: OnboardingPage by lazy {
    OnboardingPage(description = R.string.spiel_onboarding_share,
      image = R.drawable.onboarding_share,
      title = R.string.title_onboarding_share)
  }

  private val uiState: MediatorLiveData<OnboardingState> = MediatorLiveData()

  override fun onCleared() {
    OnboardingDependencyHolder.destroyOnboardingComponent()
    super.onCleared()
  }

  override fun state(): LiveData<OnboardingState> = uiState

  fun loadPages() {
    uiState.postValue(OnboardingState.PagesLoaded(listOf(pageOverview, pageShare, pageBeta)))
  }
}