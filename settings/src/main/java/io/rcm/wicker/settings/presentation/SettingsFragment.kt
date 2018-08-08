package io.rcm.wicker.settings.presentation

import android.os.Bundle
import android.preference.PreferenceFragment
import io.rcm.wicker.settings.R

/**
 * Created by joicemarinay on 08/08/2018.
 */
internal class SettingsFragment: PreferenceFragment() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    addPreferencesFromResource(R.xml.settings_preferences)
  }
}