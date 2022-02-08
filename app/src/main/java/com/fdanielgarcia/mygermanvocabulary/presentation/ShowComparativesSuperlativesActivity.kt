package com.fdanielgarcia.mygermanvocabulary.presentation

import android.os.Bundle
import com.fdanielgarcia.mygermanvocabulary.databinding.ActivityShowComparativesSuperlativesBinding

class ShowComparativesSuperlativesActivity : BaseActivity() {
    private lateinit var binding: ActivityShowComparativesSuperlativesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowComparativesSuperlativesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}