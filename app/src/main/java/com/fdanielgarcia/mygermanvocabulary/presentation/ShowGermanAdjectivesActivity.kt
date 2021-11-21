package com.fdanielgarcia.mygermanvocabulary.presentation

import android.os.Bundle
import com.fdanielgarcia.mygermanvocabulary.databinding.ActivityShowGermanAdjectivesBinding

class ShowGermanAdjectivesActivity : BaseActivity() {
    private lateinit var binding: ActivityShowGermanAdjectivesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowGermanAdjectivesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}