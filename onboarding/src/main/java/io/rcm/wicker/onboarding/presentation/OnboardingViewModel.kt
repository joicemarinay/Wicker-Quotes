package io.rcm.wicker.onboarding.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.onboarding.R
import io.rcm.wicker.onboarding.domain.usecase.ShowOnboarding
import io.rcm.wicker.onboarding.injection.OnboardingDependencyHolder
import javax.inject.Inject

/**
 * Created by joicemarinay on 02/08/2018.
 */
internal class OnboardingViewModel @Inject constructor(private val showOnboarding: ShowOnboarding):
  BaseViewModel<OnboardingState>() {

  private val pages: List<OnboardingPage> by lazy {  listOf(pageOverview, pageShare, pageBeta) }

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

  init {
    uiState.addSource(showOnboarding.liveData(), ::onShowOnboardingResult)
  }

  override fun onCleared() {
    OnboardingDependencyHolder.destroyOnboardingComponent()
    super.onCleared()
  }

  override fun state(): LiveData<OnboardingState> = uiState

  fun getStarted() {

  }

  fun loadPages() {
    showOnboarding.execute()
  }

  fun onSelectedPageChange(selectedPagePosition: Int) {
    uiState.postValue(OnboardingState.PageChange(
      isGetStartedVisible = isLastPage(selectedPagePosition)))
  }

  private fun isLastPage(position: Int): Boolean = position == pages.size - 1

  private fun onShowOnboardingResult(result: ShowOnboarding.Result?) {
    when(result) {
      is ShowOnboarding.Result.Show -> uiState.postValue(OnboardingState.PagesLoaded(pages))
      is ShowOnboarding.Result.Skip -> uiState.postValue(OnboardingState.ExitOnboarding)
    }
  }
}