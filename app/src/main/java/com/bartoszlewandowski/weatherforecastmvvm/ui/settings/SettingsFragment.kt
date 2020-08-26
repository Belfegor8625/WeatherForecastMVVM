package com.bartoszlewandowski.weatherforecastmvvm.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.bartoszlewandowski.weatherforecastmvvm.R

/**
 * Created by Bartosz Lewandowski on 17.08.2020
 */
class SettingsFragment: PreferenceFragmentCompat() {

	override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
		addPreferencesFromResource(R.xml.preferences)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		(activity as AppCompatActivity).supportActionBar?.title = "Settings"
		(activity as AppCompatActivity).supportActionBar?.subtitle = null
	}
}