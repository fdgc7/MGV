package com.fdanielgarcia.mygermanvocabulary.use_cases

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import androidx.core.os.ConfigurationCompat
import androidx.preference.PreferenceManager
import java.util.*

class LocaleManagement(base: Context): ContextWrapper(base)  {
    companion object {
        val supportedLocales = listOf("de", "en", "es")
        const val OPTION_PHONE_LANGUAGE = "sys"

        fun updateContextLocale(c: Context, localeToSwitchTo: Locale): ContextWrapper {
            var context = c
            val resources: Resources = context.resources
            val configuration: Configuration = resources.configuration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(localeToSwitchTo)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)
            } else {
                configuration.locale = localeToSwitchTo
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                context = context.createConfigurationContext(configuration)
            } else {
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }
            return LocaleManagement(context)
        }

        fun getLocaleFromPrefCode(prefCode: String): Locale{
            val localeCode = if(prefCode != OPTION_PHONE_LANGUAGE) {
                prefCode
            } else {
                val systemLang = ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0).language
                if(systemLang in supportedLocales){
                    systemLang
                } else {
                    "en"
                }
            }
            return Locale(localeCode)
        }
    }
}

class PreferredLocale(context: Context) {
    private var sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

    fun getPreferredLocale(): String {
        return sharedPref.getString("preference_language", LocaleManagement.OPTION_PHONE_LANGUAGE)!!
    }

    fun setPreferredLocale(localeCode: String) {
        sharedPref.edit().putString("preference_language", localeCode).apply()
    }
}