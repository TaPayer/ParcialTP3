package com.ar.ort.rickmorty.fragments

import com.ar.ort.rickmorty.R
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_rules, rootKey)

    }
}