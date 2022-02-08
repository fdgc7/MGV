package com.fdanielgarcia.mygermanvocabulary.presentation

import android.os.Bundle
import com.fdanielgarcia.mygermanvocabulary.databinding.ActivityShowPersonalPronounsBinding

class ShowPersonalPronounsActivity : BaseActivity() {
    // TODO: Zoom in ImageView
    private lateinit var binding: ActivityShowPersonalPronounsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowPersonalPronounsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
