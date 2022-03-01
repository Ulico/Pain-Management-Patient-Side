package com.adrianrusso.painmanagementpatientside.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adrianrusso.painmanagementpatientside.databinding.ActivityCheckinBinding
import com.adrianrusso.painmanagementpatientside.databinding.ActivityEmotionRecordBinding
import com.google.android.material.slider.Slider


class EmotionRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmotionRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmotionRecordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.feelSlider.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            "${value.toInt()}/10".also {
                binding.emotionRatingText.text = it
            }
        })
    }
}