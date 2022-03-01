package com.adrianrusso.painmanagementpatientside.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adrianrusso.painmanagementpatientside.databinding.ActivityCheckinBinding
import com.google.android.material.slider.Slider


class CheckinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.painSlider.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            "${value.toInt()}/10".also {
                binding.painLevelRatingText.text = it
            }
        })
    }
}