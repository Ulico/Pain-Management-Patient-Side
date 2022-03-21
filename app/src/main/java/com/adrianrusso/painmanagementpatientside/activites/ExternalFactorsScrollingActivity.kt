package com.adrianrusso.painmanagementpatientside.activites

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.adrianrusso.painmanagementpatientside.R
import com.adrianrusso.painmanagementpatientside.databinding.ActivityExternalFactorsScrollingBinding
import com.adrianrusso.painmanagementpatientside.databinding.ActivityMainBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Submission
import com.google.android.material.slider.Slider

class ExternalFactorsScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExternalFactorsScrollingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExternalFactorsScrollingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        binding.content.sleepSlider?.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            "${value.toInt()}/10".also {
                binding.content.sleepLevelRatingText!!.text = it
            }
        })

        binding.content.exerciseSlider?.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            "${value.toInt()}/10".also {
                binding.content.exerciseLevelRatingText!!.text = it
            }
        })

        binding.content.moodSlider?.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            "${value.toInt()}/10".also {
                binding.content.moodSliderRatingText!!.text = it
            }
        })

        binding.content.dietSlider?.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            "${value.toInt()}/10".also {
                binding.content.dietSliderRatingText!!.text = it
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSubmit(view: View) {
        val s = Submission()

        val sleepHours = binding.content.sleepDurationHours?.text.toString().toInt()
        val sleepMinutes = binding.content.sleepDurationMinutes?.text.toString().toInt()
        if (sleepHours != 0 || sleepMinutes != 0)
            s.sleepDuration = sleepHours * 60 + sleepMinutes

        val exerciseHours = binding.content.exerciseDurationHours?.text.toString().toInt()
        val exerciseMinutes = binding.content.exerciseDurationMinutes?.text.toString().toInt()
        if (exerciseHours != 0 || exerciseMinutes != 0)
            s.exerciseDuration = exerciseHours * 60 + exerciseMinutes

        s.sleepQuality = binding.content.sleepSlider?.value?.toInt()
        s.exerciseIntensity = binding.content.exerciseSlider?.value?.toInt()
        s.moodLevel = binding.content.moodSlider?.value?.toInt()
        s.bowelMovement = binding.content.yes?.isChecked
        s.dietAmount = binding.content.dietSlider?.value?.toInt()

        AppUser.sendSubmission(s)

        finish()
    }
}