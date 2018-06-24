package io.rcm.wicker.base.injection.scopes

import javax.inject.Scope

/**
 * All credits go to MojRoid
 *
 * @see https://github.com/MojRoid/memes/blob/master/base/src/main/java/moj/memes/base/injection/scopes/PerApplication.kt
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerApplication