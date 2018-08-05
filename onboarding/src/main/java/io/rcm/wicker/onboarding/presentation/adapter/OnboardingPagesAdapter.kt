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

  private val pages: MutableList<OnboardingPage> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingPageViewHolder =
    OnboardingPageViewHolder(parent.inflate(R.layout.wicker_onboarding_page))

  override fun getItemCount(): Int = pages.size

  override fun onBindViewHolder(holder: OnboardingPageViewHolder, position: Int) {
    holder.bind(pages[position])
  }

  fun setPages(pages: List<OnboardingPage>) {
    this.pages.clear()
    this.pages.addAll(pages)
    notifyDataSetChanged()
  }
}