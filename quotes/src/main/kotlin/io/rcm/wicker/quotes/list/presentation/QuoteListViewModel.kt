package io.rcm.wicker.quotes.list.presentation

import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.quotes.QuotesDependencyHolder
import javax.inject.Inject

/**
 * Created by joicemarinay on 6/24/18.
 */
internal class QuoteListViewModel @Inject constructor(): BaseViewModel() {

  //STUDY why destroy component in ViewMode.onCleared() instead of in Activity.onDestroy()
  override fun onCleared() {
    QuotesDependencyHolder.destroyListComponent()
    super.onCleared()
  }
}