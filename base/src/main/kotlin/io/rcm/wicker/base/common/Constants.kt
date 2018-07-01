package io.rcm.wicker.base.common

import io.rcm.wicker.base.BuildConfig

/**
 * Created by joicemarinay on 7/1/18.
 */

/**
 * This value SHOULD be the prefix of any key that is internal to this project.
 *
 * Examples of keys:
 * - Intent extra
 * - SharedPreferences
 */
val KEY_PREFIX = "${BuildConfig.APPLICATION_ID}.${BuildConfig.BUILD_TYPE}"