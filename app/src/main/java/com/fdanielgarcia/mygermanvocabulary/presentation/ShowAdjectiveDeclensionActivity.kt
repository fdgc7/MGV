package com.fdanielgarcia.mygermanvocabulary.presentation

import android.os.Bundle
import com.fdanielgarcia.mygermanvocabulary.databinding.ActivityShowAdjectiveDeclensionBinding

class ShowAdjectiveDeclensionActivity : BaseActivity() {
    // TODO: Zoom in ImageView
    private lateinit var binding: ActivityShowAdjectiveDeclensionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowAdjectiveDeclensionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
