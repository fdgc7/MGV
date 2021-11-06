package com.fdanielgarcia.mygermanvocabulary.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.preference.*
import com.fdanielgarcia.mygermanvocabulary.R

class SettingsFragment : PreferenceFragmentCompat() {
    companion object {
        const val INF_NUM_CHAR_LIMIT = 0
        const val SUP_NUM_CHAR_LIMIT = 10
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val searchingMinChars =
            findPreference<EditTextPreference>("edit_text_preference_searching_min_chars")

        searchingMinChars?.setOnPreferenceChangeListener { preference, newValue ->
            var value = -1
            try {
                value = Integer.parseInt(newValue as String)
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    activity,
                    activity?.resources?.getString(R.string.incorrect_value),
                    Toast.LENGTH_LONG
                ).show()
                false
            }
            if (value < INF_NUM_CHAR_LIMIT || value > SUP_NUM_CHAR_LIMIT) {
                Toast.makeText(
                    activity,
                    activity?.resources?.getString(R.string.incorrect_value),
                    Toast.LENGTH_LONG
                ).show()
                false
            } else { true }
        }
    }
}

