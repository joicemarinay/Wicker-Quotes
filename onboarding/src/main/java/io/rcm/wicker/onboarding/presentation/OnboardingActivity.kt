package io.rcm.wicker.onboarding.presentation

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import io.rcm.wicker.base.common.observe
import io.rcm.wicker.base.common.setVisibilityByBoolean
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.onboarding.R
import io.rcm.wicker.onboarding.common.LinePagerIndicatorDecoration
import io.rcm.wicker.onboarding.injection.OnboardingComponent
import io.rcm.wicker.onboarding.injection.OnboardingDependencyHolder
import io.rcm.wicker.onboarding.presentation.OnboardingState.*
import io.rcm.wicker.onboarding.presentation.adapter.OnboardingPagesAdapter
import kotlinx.android.synthetic.main.wicker_onboarding_view.*

/**
 * Created by joicemarinay on 02/08/2018.
 */
internal class OnboardingActivity(override val layoutResourceId: Int = R.layout.wicker_onboarding_view):
  BaseActivity<OnboardingViewModel, OnboardingState>() {

  private val pagesAdapter: OnboardingPagesAdapter by lazy { OnboardingPagesAdapter() }
  private val component: OnboardingComponent by lazy { OnboardingDependencyHolder.onboardingComponent() }

  override fun onCreate(savedInstanceState: Bundle?) {
    component.inject(this)
    super.onCreate(savedInstanceState)
    setDataObservers()
    setPagesScrollListener()
    setRecyclerViewPages()
    viewModel.loadPages()
  }

  override fun onStateChange(state: OnboardingState) {
    when(state) {
      is PageChange -> setGetStarted(state.isGetStartedVisible)
      is PagesLoaded -> setPages(state.pages)
    }
  }

  private fun setDataObservers() {
    observe(viewModel.state()) { onStateChange(it) }
  }

  private fun setGetStarted(isVisible: Boolean) {
    onboarding_button_getstarted.setVisibilityByBoolean(isVisible)
  }

  private fun setPages(pages: List<OnboardingPage>) {
    pagesAdapter.setPages(pages)
  }

  private fun setPagesScrollListener() {
    onboarding_recyclerview_pages.addOnScrollListener(object: RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        viewModel.onSelectedPageChange((recyclerView.layoutManager as LinearLayoutManager).
          findLastVisibleItemPosition())
      }
    })
  }

  private fun setRecyclerViewPages() {
    onboarding_recyclerview_pages?.layoutManager =
      LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    //[PagerSnapHelper] makes the RecyclerView behave like a ViewPager that displays one item per "page"
    PagerSnapHelper().attachToRecyclerView(onboarding_recyclerview_pages)
    onboarding_recyclerview_pages.addItemDecoration(LinePagerIndicatorDecoration(this))
    onboarding_recyclerview_pages.adapter = pagesAdapter
  }
}