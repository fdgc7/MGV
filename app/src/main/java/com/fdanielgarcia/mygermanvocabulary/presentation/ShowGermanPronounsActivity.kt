package com.fdanielgarcia.mygermanvocabulary.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fdanielgarcia.mygermanvocabulary.databinding.ActivityShowGermanPronounsBinding

class ShowGermanPronounsActivity : BaseActivity() {
    // TODO: Zoom in ImageView
    private lateinit var binding: ActivityShowGermanPronounsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowGermanPronounsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
