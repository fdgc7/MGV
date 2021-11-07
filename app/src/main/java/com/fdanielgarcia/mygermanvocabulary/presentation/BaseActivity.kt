package com.fdanielgarcia.mygermanvocabulary.presentation

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fdanielgarcia.mygermanvocabulary.MGVApplication
import com.fdanielgarcia.mygermanvocabulary.use_cases.LocaleManagement
import com.fdanielgarcia.mygermanvocabulary.use_cases.PreferredLocale

open class BaseActivity: AppCompatActivity() { //You can use your preferred activity instead of AppCompatActivity
    private lateinit var oldPrefLocaleCode : String

    /**
     * updates the toolbar text locale if it set from the android:label property of Manifest
     */
    private fun resetTitle() {
        try {
            val label = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA).labelRes;
            if (label != 0) {
                setTitle(label);
            }
        } catch (e: PackageManager.NameNotFoundException) {}
    }

    override fun attachBaseContext(newBase: Context) {
        oldPrefLocaleCode = PreferredLocale(newBase).getPreferredLocale()
        applyOverrideConfiguration(LocaleManagement.getLocalizedConfiguration(oldPrefLocaleCode))
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetTitle()
    }

    override fun onResume() {
        val currentLocaleCode = PreferredLocale(this).getPreferredLocale()
        if(oldPrefLocaleCode != currentLocaleCode){
            recreate() //locale is changed, restart the activity to update
            oldPrefLocaleCode = currentLocaleCode
        }
        super.onResume()
    }
}