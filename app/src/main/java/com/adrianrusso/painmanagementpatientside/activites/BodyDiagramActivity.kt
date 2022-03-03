package com.adrianrusso.painmanagementpatientside.activites

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import com.adrianrusso.painmanagementpatientside.R
import com.adrianrusso.painmanagementpatientside.databinding.ActivityBodyDiagramBinding
import com.adrianrusso.painmanagementpatientside.databinding.ActivityMainBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Submission

class BodyDiagramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBodyDiagramBinding
    private var painLocation: Pair<Float, Float> = Pair(0F, 0F)

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyDiagramBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bodyDiagram.setOnTouchListener { p0, p1 ->
            if (p1.action == MotionEvent.ACTION_DOWN) {
                require(p1 != null)

                painLocation = Pair(p1.x.div(p0.width), p1.y.div(p0.height))

                binding.circle.visibility = View.VISIBLE
                binding.circle.x = p1.x - (binding.circle.width * 2) / 3
                binding.circle.y = p1.y + (binding.circle.height * 3) / 2
            }
            true
        }

    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun onSubmit(view: View) {
        val s = Submission()
        s.painCoordinates = painLocation
        AppUser.sendSubmission(s)

        startActivity(Intent(this, CheckinActivity::class.java))
    }
}