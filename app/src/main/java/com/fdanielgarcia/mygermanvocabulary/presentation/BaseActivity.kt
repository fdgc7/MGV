package com.fdanielgarcia.mygermanvocabulary.presentation

import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fdanielgarcia.mygermanvocabulary.MGVApplication
import com.fdanielgarcia.mygermanvocabulary.use_cases.LocaleManagement
import com.fdanielgarcia.mygermanvocabulary.use_cases.PreferredLocale

open class BaseActivity: AppCompatActivity() { //You can use your preferred activity instead of AppCompatActivity
    private lateinit var oldPrefLocaleCode : String
    protected val preferredLocale : PreferredLocale by lazy {
        (application as MGVApplication).preferredLocale
    }

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
        val currentLocaleCode = PreferredLocale(newBase).getPreferredLocale()
        val prefLocale = LocaleManagement.getLocaleFromPrefCode(currentLocaleCode)
        val localeUpdatedContext: ContextWrapper = LocaleManagement.updateContextLocale(newBase, prefLocale)
        oldPrefLocaleCode = currentLocaleCode
        super.attachBaseContext(localeUpdatedContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MGVApplication).invalidateConfiguration()
        resetTitle()
    }

    override fun onResume() {
        val currentLocaleCode = PreferredLocale(this).getPreferredLocale()
        if(oldPrefLocaleCode != currentLocaleCode){
            (application as MGVApplication).invalidateConfiguration()
            recreate()
            oldPrefLocaleCode = currentLocaleCode
        }
        super.onResume()
    }
}