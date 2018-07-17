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
import io.rcm.wicker.quotes.domain.usecase.DeleteQuote
import io.rcm.wicker.quotes.domain.usecase.DeleteQuoteUseCase
import io.rcm.wicker.quotes.domain.usecase.SoftDeleteQuote
import io.rcm.wicker.quotes.domain.usecase.SoftDeleteUseCase
import io.rcm.wicker.quotes.features.list.injection.QuoteListScope
import io.rcm.wicker.quotes.presentation.QuotesUiMapper
import io.rcm.wicker.quotes.presentation.ResourceProvider
import io.rcm.wicker.quotes.presentation.ResourceProviderImpl

/**
 * Created by joicemarinay on 26/06/2018.
 *
 * Contains common dependencies across features in quotes module
 *
 * TODO group by layer
 */
@Module
internal class QuotesModule {

  @Provides
  @QuoteListScope
  fun deleteQuoteUseCase(repository: QuotesRepository): DeleteQuote =
    DeleteQuoteUseCase(repository)

  @Provides
  @QuoteListScope
  fun softDeleteQuoteUseCase(mapper: QuotesUiMapper, repository: QuotesRepository): SoftDeleteQuote =
    SoftDeleteUseCase(mapper, repository)

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

  @Provides
  @QuoteListScope
  fun resourceProvider(context: Context): ResourceProvider = ResourceProviderImpl(context)

}