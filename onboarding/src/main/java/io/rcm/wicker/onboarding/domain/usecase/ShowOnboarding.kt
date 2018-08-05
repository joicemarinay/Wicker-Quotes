package io.rcm.wicker.onboarding.domain.usecase

import io.rcm.wicker.base.domain.UseCase

/**
 * Created by joicemarinay on 06/08/2018.
 */
internal interface ShowOnboarding: UseCase<ShowOnboarding.Result> {

  sealed class Result {
    object Show: Result()
    object Skip: Result()
  }

  fun execute()
}