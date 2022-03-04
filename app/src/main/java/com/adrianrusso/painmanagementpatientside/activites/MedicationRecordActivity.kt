package com.adrianrusso.painmanagementpatientside.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.adrianrusso.painmanagementpatientside.R
import com.adrianrusso.painmanagementpatientside.adapters.MedicationPagerAdapter
import com.adrianrusso.painmanagementpatientside.databinding.ActivityMainBinding
import com.adrianrusso.painmanagementpatientside.databinding.ActivityMedicationRecordBinding

class MedicationRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicationRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicationRecordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.horizontalScrollView.adapter = MedicationPagerAdapter(this)
    }

    fun onSubmit(view: View) {
        startActivity(Intent(this, CheckinActivity::class.java))
    }

    fun onAddNew(view: View) {
        startActivity(Intent(this, AddMedicationActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        binding.horizontalScrollView.adapter?.notifyDataSetChanged()
    }
}