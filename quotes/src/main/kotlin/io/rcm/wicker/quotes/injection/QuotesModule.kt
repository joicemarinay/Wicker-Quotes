package io.rcm.wicker.quotes.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import io.rcm.wicker.quotes.data.local.QuotesLocalDataSource
import io.rcm.wicker.quotes.data.local.QuotesLocalMapper
import io.rcm.wicker.quotes.data.local.QuotesLocalRepository
import io.rcm.wicker.quotes.data.local.QuotesLocalSource
import io.rcm.wicker.quotes.data.local.db.QuotesDb
import io.rcm.wicker.quotes.domain.QuotesRepository
import io.rcm.wicker.quotes.features.list.injection.QuoteListScope

/**
 * Created by joicemarinay on 26/06/2018.
 *
 * Contains common dependencies across features in quotes module
 */
@Module
internal class QuotesModule {

  @Provides
  @QuoteListScope
  fun provideDb(context: Context): QuotesDb = QuotesDb.getInstance(context)

  @Provides
  @QuoteListScope
  fun localDataSource(db: QuotesDb, mapper: QuotesLocalMapper): QuotesLocalSource =
    QuotesLocalDataSource(db, mapper)

  @Provides
  @QuoteListScope
  fun repository(localSource: QuotesLocalSource): QuotesRepository =
    QuotesLocalRepository(localSource)
}