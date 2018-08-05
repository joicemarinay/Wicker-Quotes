package io.rcm.wicker.onboarding.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.rcm.wicker.base.common.inflate
import io.rcm.wicker.onboarding.R
import io.rcm.wicker.onboarding.presentation.OnboardingPage

/**
 * Created by joicemarinay on 03/08/2018.
 */
internal class OnboardingPagesAdapter: RecyclerView.Adapter<OnboardingPageViewHolder>() {

  private val pages: List<OnboardingPage> by lazy { listOf(pageOverview, pageShare, pageBeta) }

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

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingPageViewHolder =
    OnboardingPageViewHolder(parent.inflate(R.layout.wicker_onboarding_page))

  override fun getItemCount(): Int = pages.size

  override fun onBindViewHolder(holder: OnboardingPageViewHolder, position: Int) {
    holder.bind(pages[position])
  }
}