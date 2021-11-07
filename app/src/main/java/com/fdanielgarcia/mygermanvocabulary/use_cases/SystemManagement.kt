package com.fdanielgarcia.mygermanvocabulary.use_cases

import android.app.Activity
import android.content.res.Configuration
import android.widget.Toast
import com.fdanielgarcia.mygermanvocabulary.R
import java.util.*

class SystemManagement (val activity: Activity) {
    fun changeAppLanguage (language: String) {
        var locale = Locale("en")

        when (language) {
            activity.resources?.getString(R.string.settings_language_system) -> {
                Toast.makeText(
                    activity,
                    "sys",
                    Toast.LENGTH_LONG
                ).show()
            }
            activity.resources?.getString(R.string.settings_language_german) -> {
                locale = Locale("de")
            }
            activity.resources?.getString(R.string.settings_language_english) -> {
                locale = Locale("en")
            }
            activity.resources?.getString(R.string.settings_language_spanish) -> {
                locale = Locale("es")
            }
        }

        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        activity.baseContext.resources.updateConfiguration(config, null)
    }
}