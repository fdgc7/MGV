package com.fdanielgarcia.mygermanvocabulary.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.fdanielgarcia.mygermanvocabulary.MGVApplication
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.databinding.ActivityMainBinding
import com.fdanielgarcia.mygermanvocabulary.use_cases.AppInformation
import com.fdanielgarcia.mygermanvocabulary.use_cases.ListManagement

class MainActivity : BaseActivity() {
    val appInformation by lazy { AppInformation(this) }
    val listManagement by lazy { ListManagement(this) }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    val vocabularyDB by lazy { (application as MGVApplication).vocabularyDB }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle(this.resources?.getString(R.string.stored))
                .setMessage(listManagement.showStored())
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_search -> {
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_All_to_SearchFragment)
                true
            }
            R.id.action_german_pronouns -> {
                val i = Intent(this, ShowGermanPronounsActivity::class.java)
                startActivity(i)
                true
            }
            R.id.action_german_prepositions -> {
                val i = Intent(this, ShowGermanPrepositionsActivity::class.java)
                startActivity(i)
                true
            }
            R.id.action_german_adjectives -> {
                val i = Intent(this, ShowGermanAdjectivesActivity::class.java)
                startActivity(i)
                true
            }
            R.id.action_settings -> {
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_All_to_SettingsFragment)
                true
            }
            R.id.action_about -> {
                AlertDialog.Builder(this)
                    .setTitle(this.resources?.getString(R.string.action_about))
                    .setMessage(appInformation.showVersion())
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        vocabularyDB.close()
        super.onDestroy()
    }

    fun showFab() {
        binding.fab.visibility = View.VISIBLE
    }

    fun hideFab() {
        binding.fab.visibility = View.INVISIBLE
    }
}