package io.rcm.wicker.base.domain

import android.arch.lifecycle.LiveData

/**
 * Source:
 * https://github.com/MojRoid/memes/blob/master/base/src/main/java/moj/memes/base/domain/UseCase.kt
 */
interface UseCase<T> {

  fun liveData(): LiveData<T>

  fun cleanUp()
}