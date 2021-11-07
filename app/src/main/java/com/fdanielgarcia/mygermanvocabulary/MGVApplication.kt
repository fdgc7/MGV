package com.fdanielgarcia.mygermanvocabulary

import android.app.Application
import android.content.Context
import com.fdanielgarcia.mygermanvocabulary.data.VocabularyDB
import com.fdanielgarcia.mygermanvocabulary.use_cases.LocaleManagement
import com.fdanielgarcia.mygermanvocabulary.use_cases.PreferredLocale

class MGVApplication : Application() {
    val vocabularyDB = VocabularyDB(this)

    val preferredLocale: PreferredLocale by lazy {
        PreferredLocale(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManagement.getLocalizedContext(base, PreferredLocale(base).getPreferredLocale()))
    }
}