package com.adrianrusso.painmanagementpatientside.activites

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.adrianrusso.painmanagementpatientside.R
import com.adrianrusso.painmanagementpatientside.databinding.ActivityDailyLogBinding
import com.google.android.material.slider.Slider

class DailyLog : AppCompatActivity() {

    private lateinit var binding: ActivityDailyLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyLogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        binding.content.painSlider?.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            "${value.toInt()}/10".also {
                binding.content.painText!!.text = it
            }
        })

        binding.content.feelSlider?.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            "${value.toInt()}/10".also {
                binding.content.feelScoreText!!.text = it
            }
        })

        binding.content.feelSlider2?.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            "${value.toInt()}/10".also {
                binding.content.feelScoreText2!!.text = it
            }
        })


    }

    fun onSubmit(view: View) {
        startActivity(Intent(this, NavigationActivity::class.java))
    }
}