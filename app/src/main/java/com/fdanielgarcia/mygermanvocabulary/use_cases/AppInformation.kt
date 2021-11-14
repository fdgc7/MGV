package com.fdanielgarcia.mygermanvocabulary.use_cases

import android.app.Activity
import com.fdanielgarcia.mygermanvocabulary.BuildConfig
import com.fdanielgarcia.mygermanvocabulary.R

class AppInformation(val activity: Activity) {
    fun showVersion(): String {
        val message = "Version " +
                BuildConfig.VERSION_NAME + " (" +
                BuildConfig.VERSION_CODE + ") " +
                "\n" +
                "\n" +
                "\n" +
                activity.getString(R.string.main_icons_license) +
                "\n" +
                "\n" +
                activity.getString(R.string.material_design_icons_license)
        return message
    }
}