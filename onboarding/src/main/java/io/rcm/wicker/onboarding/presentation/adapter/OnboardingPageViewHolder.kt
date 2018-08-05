package io.rcm.wicker.onboarding.presentation.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import io.rcm.wicker.onboarding.presentation.OnboardingPage
import kotlinx.android.synthetic.main.wicker_onboarding_page.view.*

/**
 * Created by joicemarinay on 03/08/2018.
 */
internal class OnboardingPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(page: OnboardingPage) = with(itemView) {
    onboarding_page_text_description.setText(page.description)
    onboarding_page_image.setImageDrawable(ContextCompat.getDrawable(itemView.context, page.image))
    onboarding_page_text_title.setText(page.title)
  }

}