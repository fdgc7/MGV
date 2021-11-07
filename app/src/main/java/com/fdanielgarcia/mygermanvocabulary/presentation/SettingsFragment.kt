package com.fdanielgarcia.mygermanvocabulary.presentation

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.preference.*
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.use_cases.ListManagement
import com.fdanielgarcia.mygermanvocabulary.use_cases.SystemManagement

class SettingsFragment : PreferenceFragmentCompat() {
    val systemManagement by lazy { SystemManagement(activity as Activity) }

    companion object {
        const val INF_NUM_CHAR_LIMIT = 0
        const val SUP_NUM_CHAR_LIMIT = 10
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val language = findPreference<ListPreference>("preference_language")
        val searchingMinChars =
            findPreference<EditTextPreference>("preference_searching_min_chars")

        language?.setOnPreferenceChangeListener { _, newValue ->
            systemManagement.changeAppLanguage(newValue as String)
            true
        }

        searchingMinChars?.setOnPreferenceChangeListener { _, newValue ->
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

