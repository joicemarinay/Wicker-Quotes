package io.rcm.wicker.app.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.rcm.wicker.base.injection.scopes.PerActivity
import io.rcm.wicker.quotelist.presentation.QuoteListActivity
import io.rcm.wicker.writequote.presentation.WriteQuoteActivity

/**
 * Created by joicemarinay on 16/05/2018.
 */
@Module
internal abstract class Bindings {

  @PerActivity
  @ContributesAndroidInjector
  abstract fun bindQuoteListActivity(): QuoteListActivity

  @PerActivity
  @ContributesAndroidInjector
  abstract fun bindWriteQuoteActivity(): WriteQuoteActivity
}