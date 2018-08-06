package io.rcm.wicker.onboarding.domain.usecase

import io.rcm.wicker.base.domain.UseCase

/**
 * Created by joicemarinay on 06/08/2018.
 */
internal interface CompleteOnboarding: UseCase<CompleteOnboarding.Result> {

  sealed class Result

  fun execute()
}