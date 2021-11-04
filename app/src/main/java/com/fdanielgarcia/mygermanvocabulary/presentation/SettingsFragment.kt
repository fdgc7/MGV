package com.fdanielgarcia.mygermanvocabulary.presentation

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.fdanielgarcia.mygermanvocabulary.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)


    }
}