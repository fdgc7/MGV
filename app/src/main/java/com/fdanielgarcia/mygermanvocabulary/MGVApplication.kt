package com.fdanielgarcia.mygermanvocabulary

import android.app.Application
import android.view.View
import com.fdanielgarcia.mygermanvocabulary.data.VocabularyDB

class MGVApplication : Application() {
    val vocabularyDB = VocabularyDB(this)
}