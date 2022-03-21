package com.adrianrusso.painmanagementpatientside.activites

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import com.adrianrusso.painmanagementpatientside.databinding.ActivityCheckinBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Submission
import com.google.android.material.slider.Slider
import com.google.android.material.snackbar.Snackbar


class CheckinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckinBinding
    private var painLocation: MutableList<Pair<Float, Float>> =
        mutableListOf(Pair(0F, 0F), Pair(0F, 0F), Pair(0F, 0F))
    private var clicks = 0

    @SuppressLint("ClickableViewAccessibility")
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

        binding.bodyDiagram.setOnTouchListener { p0, p1 ->
            if (p1.action == MotionEvent.ACTION_DOWN) {

                require(p1 != null)

                painLocation[clicks % 3] = Pair(p1.x.div(p0.width), p1.y.div(p0.height))
                clicks++

                var circle = binding.circle
                if (clicks % 3 == 2)
                    circle = binding.circle2
                else if (clicks % 3 == 0)
                    circle = binding.circle3
                circle.visibility = View.VISIBLE
                circle.x = p1.x - (circle.width * 2) / 3
                circle.y = p1.y + (circle.height * 2)
            }
            true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSubmit(view: View) {
        val s = Submission()
        s.painLevel = binding.painSlider.value.toInt()

        Snackbar.make(view, "Pain level submitted", Snackbar.LENGTH_SHORT).show()
        s.painCoordinates = painLocation.toList()
        AppUser.sendSubmission(s)

    }

    fun onPainDescription(view: View) {
        startActivity(Intent(this, PainDescriptionScrollingActivity::class.java))
    }

    fun onExternalFactors(view: View) {
        startActivity(Intent(this, ExternalFactorsScrollingActivity::class.java))
    }

    fun onTreatmentStrategies(view: View) {
        startActivity(Intent(this, TreatmentStrategiesScrollingActivity::class.java))
    }
}