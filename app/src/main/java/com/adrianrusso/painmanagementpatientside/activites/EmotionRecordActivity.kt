package com.adrianrusso.painmanagementpatientside.activites

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.adrianrusso.painmanagementpatientside.databinding.ActivityCheckinBinding
import com.adrianrusso.painmanagementpatientside.databinding.ActivityEmotionRecordBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Submission
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSubmit(view: View) {
        val s = Submission()
        s.feelLevel = binding.feelSlider.value.toInt()
        AppUser.sendSubmission(s)

        finish()
    }
}