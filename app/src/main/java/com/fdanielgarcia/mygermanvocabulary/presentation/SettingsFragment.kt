package com.fdanielgarcia.mygermanvocabulary.presentation

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.preference.*
import com.fdanielgarcia.mygermanvocabulary.MGVApplication
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.use_cases.PreferredLocale
import java.util.*

class SettingsFragment : PreferenceFragmentCompat() {
    val preferredLocale : PreferredLocale by lazy { (activity?.application as MGVApplication).preferredLocale }

    companion object {
        const val INF_NUM_CHAR_LIMIT = 0
        const val SUP_NUM_CHAR_LIMIT = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_german_pronouns).setVisible(false)
        menu.findItem(R.id.action_german_prepositions).setVisible(false)
        menu.findItem(R.id.action_settings).setVisible(false)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val language = findPreference<ListPreference>("preference_language")
        val searchingMinChars =
            findPreference<EditTextPreference>("preference_searching_min_chars")

        language?.setOnPreferenceChangeListener { _, newValue ->
            Locale.setDefault(Locale(newValue as String))
            preferredLocale.setPreferredLocale(newValue as String)
            recreate(activity as Activity)
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

