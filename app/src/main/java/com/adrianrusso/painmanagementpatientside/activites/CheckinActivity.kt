package com.adrianrusso.painmanagementpatientside.activites

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.adrianrusso.painmanagementpatientside.databinding.ActivityCheckinBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Submission
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

    fun onBodyDiagram(view: View) {
        startActivity(Intent(this, BodyDiagramActivity::class.java))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSubmit(view: View) {
        val s = Submission()
        s.painLevel = binding.painSlider.value.toInt()
        AppUser.sendSubmission(s)

    }

    fun onLifeRecord(view: View) {
        startActivity(Intent(this, EmotionRecordActivity::class.java))
    }
    fun onMedication(view: View) {
        startActivity(Intent(this, MedicationRecordActivity::class.java))
    }
}