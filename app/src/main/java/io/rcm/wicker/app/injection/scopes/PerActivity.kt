package io.rcm.wicker.app.injection.scopes

import javax.inject.Scope

/**
 *
 * All credits go to MojRoid
 *
 * @see https://github.com/MojRoid/memes/blob/master/base/src/main/java/moj/memes/base/injection/scopes/PerActivity.kt
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity