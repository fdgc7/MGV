package com.fdanielgarcia.mygermanvocabulary.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fdanielgarcia.mygermanvocabulary.databinding.ActivityShowGermanPrepositionsBinding

class ShowGermanPrepositionsActivity : BaseActivity() {
    private lateinit var binding: ActivityShowGermanPrepositionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowGermanPrepositionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}