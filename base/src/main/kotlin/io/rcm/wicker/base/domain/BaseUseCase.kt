package io.rcm.wicker.base.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Source:
 * https://github.com/MojRoid/memes/blob/master/base/src/main/java/moj/memes/base/domain/BaseUseCase.kt
 */
abstract class BaseUseCase<T>(
  private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
  protected val liveData: MutableLiveData<T> = MutableLiveData()) : UseCase<T> {

  protected fun Disposable.track() {
    compositeDisposable.add(this)
  }

  override fun liveData(): LiveData<T> = liveData

  override fun cleanUp() {
    compositeDisposable.clear()
  }
}