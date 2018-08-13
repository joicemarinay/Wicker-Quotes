package io.rcm.wicker.quotes.injection

import android.arch.lifecycle.MediatorLiveData
import android.content.Context
import dagger.Module
import dagger.Provides
import io.rcm.wicker.quotes.data.local.QuotesLocalDataSource
import io.rcm.wicker.quotes.data.local.QuotesLocalMapper
import io.rcm.wicker.quotes.data.local.QuotesLocalRepository
import io.rcm.wicker.quotes.data.local.QuotesLocalSource
import io.rcm.wicker.quotes.data.local.db.QuotesDb
import io.rcm.wicker.quotes.domain.QuotesRepository
import io.rcm.wicker.quotes.domain.usecase.ChangeDeleteState
import io.rcm.wicker.quotes.domain.usecase.ChangeDeleteUseCase
import io.rcm.wicker.quotes.domain.usecase.DeleteQuote
import io.rcm.wicker.quotes.domain.usecase.DeleteQuoteUseCase
import io.rcm.wicker.quotes.features.list.injection.QuoteListScope
import io.rcm.wicker.quotes.features.list.presentation.QuoteListState
import io.rcm.wicker.quotes.presentation.QuotesUiMapper
import io.rcm.wicker.quotes.presentation.ResourceProvider
import io.rcm.wicker.quotes.presentation.ResourceProviderImpl

/**
 * Created by joicemarinay on 26/06/2018.
 *
 * Contains common dependencies across features in quotes module
 *
 */
@Module(includes = [
  QuotesModule.Data::class,
  QuotesModule.Domain::class,
  QuotesModule.Presentation::class
])
internal class QuotesModule {

  @Module
  internal class Data {

    @Provides
    @QuoteListScope
    fun database(context: Context): QuotesDb = QuotesDb.getInstance(context)

    @Provides
    @QuoteListScope
    fun localDataSource(db: QuotesDb, mapper: QuotesLocalMapper): QuotesLocalSource =
      QuotesLocalDataSource(db, mapper)

    @Provides
    @QuoteListScope
    fun repository(localSource: QuotesLocalSource): QuotesRepository =
      QuotesLocalRepository(localSource)
  }

  @Module
  internal class Domain {

    @Provides
    @QuoteListScope
    fun changeDeleteStateUseCase(mapper: QuotesUiMapper, repository: QuotesRepository):
      ChangeDeleteState = ChangeDeleteUseCase(mapper, repository)

    @Provides
    @QuoteListScope
    fun deleteQuoteUseCase(repository: QuotesRepository): DeleteQuote =
      DeleteQuoteUseCase(repository)

  }

  @Module
  internal class Presentation {

    @Provides
    @QuoteListScope
    fun quoteListState(): MediatorLiveData<QuoteListState> = MediatorLiveData()

    @Provides
    @QuoteListScope
    fun resourceProvider(context: Context): ResourceProvider = ResourceProviderImpl(context)
  }

}