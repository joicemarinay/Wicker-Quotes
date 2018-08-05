package io.rcm.wicker.onboarding.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.onboarding.R
import io.rcm.wicker.onboarding.common.LinePagerIndicatorDecoration
import io.rcm.wicker.onboarding.presentation.adapter.OnboardingPagesAdapter
import kotlinx.android.synthetic.main.wicker_onboarding_view.*

/**
 * Created by joicemarinay on 02/08/2018.
 */
internal class OnboardingActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.wicker_onboarding_view)
    setRecyclerViewPages()
  }

  private fun setRecyclerViewPages() {
    onboarding_recyclerview_pages?.layoutManager =
      LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    //[PagerSnapHelper] makes the RecyclerView behave like a ViewPager that displays one item per "page"
    PagerSnapHelper().attachToRecyclerView(onboarding_recyclerview_pages)
    onboarding_recyclerview_pages.addItemDecoration(LinePagerIndicatorDecoration(this))
    onboarding_recyclerview_pages.adapter = OnboardingPagesAdapter()
  }
}