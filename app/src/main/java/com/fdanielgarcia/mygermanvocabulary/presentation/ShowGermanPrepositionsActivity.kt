package com.fdanielgarcia.mygermanvocabulary.presentation

import android.os.Bundle
import com.fdanielgarcia.mygermanvocabulary.databinding.ActivityShowPrepositionsBinding

class ShowGermanPrepositionsActivity : BaseActivity() {
    private lateinit var binding: ActivityShowPrepositionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowPrepositionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}