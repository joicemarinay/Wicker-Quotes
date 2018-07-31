package io.rcm.wicker.base.common

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Source:
 * https://github.com/MojRoid/memes/blob/master/base/src/test/java/moj/memes/base/rule/SchedulerRule.kt
 *
 * Test rule that overrides all schedulers with the trampoline scheduler.
 * This means everything runs on the same thread, when using this rule
 */
class SchedulerRule : TestRule {

  override fun apply(base: Statement, description: Description): Statement {
    return object : Statement() {
      override fun evaluate() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        try {
          base.evaluate()
        } finally {
          RxJavaPlugins.reset()
          RxAndroidPlugins.reset()
        }
      }
    }
  }
}
