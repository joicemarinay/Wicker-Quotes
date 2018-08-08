package io.rcm.wicker.settings.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by joicemarinay on 08/08/2018.
 */
internal class SettingsActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (fragmentManager.findFragmentById(android.R.id.content) == null) {
      fragmentManager.beginTransaction().add(android.R.id.content, SettingsFragment()).commit()
    }
  }
}