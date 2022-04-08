package com.adrianrusso.painmanagementpatientside.activites

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.adrianrusso.painmanagementpatientside.databinding.ActivityCheckinBinding
import com.adrianrusso.painmanagementpatientside.databinding.ActivityEmotionRecordBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Submission
import com.google.android.material.slider.Slider
import com.google.android.material.snackbar.Snackbar


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

        binding.treatmentsSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, AppUser.commonTreatments)
        binding.activitesSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, AppUser.activities)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSubmit(view: View) {
        val s = Submission()
        s.feelLevel = binding.feelSlider.value.toInt()
        s.treatments = listOf(binding.treatmentsSpinner.selectedItem.toString())
        s.activity = binding.activitesSpinner.selectedItem.toString()
        AppUser.sendSubmission(s, false)

        Snackbar.make(view, "Emotion level submitted", Snackbar.LENGTH_SHORT).show()

        finish()
    }
}