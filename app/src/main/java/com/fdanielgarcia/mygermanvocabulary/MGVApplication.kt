package com.fdanielgarcia.mygermanvocabulary

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import com.fdanielgarcia.mygermanvocabulary.data.VocabularyDB
import com.fdanielgarcia.mygermanvocabulary.use_cases.LocaleManagement
import com.fdanielgarcia.mygermanvocabulary.use_cases.PreferredLocale

class MGVApplication : Application() {
    val vocabularyDB = VocabularyDB(this)

    private var currentLocaleContext: Context? = null
    val preferredLocale: PreferredLocale by lazy {
        PreferredLocale(this)
    }

    fun invalidateConfiguration(){
        //updating app configuration according to locale change
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(LocaleManagement.getLocaleFromPrefCode(PreferredLocale(this).getPreferredLocale()))
        currentLocaleContext = createConfigurationContext(configuration)
    }

    fun getStringInLocale(@StringRes stringRes: Int, vararg formatArgs: Any): String {
        //getting string from app context
        return currentLocaleContext?.resources?.getString(stringRes, *formatArgs) ?: ""
    }
}