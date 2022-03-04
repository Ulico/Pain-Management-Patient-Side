package com.adrianrusso.painmanagementpatientside.activites

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.adrianrusso.painmanagementpatientside.R
import com.adrianrusso.painmanagementpatientside.adapters.MedicationPagerAdapter
import com.adrianrusso.painmanagementpatientside.databinding.ActivityMainBinding
import com.adrianrusso.painmanagementpatientside.databinding.ActivityMedicationRecordBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Submission
import com.google.android.material.snackbar.Snackbar

class MedicationRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicationRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicationRecordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.horizontalScrollView.adapter = MedicationPagerAdapter(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSubmit(view: View) {
        val s = Submission()
        s.finishedMedications = binding.checkBox.isChecked
        s.notes = binding.notesInput.text.toString()
        AppUser.sendSubmission(s)

        Snackbar.make(view, "Medication info submitted", Snackbar.LENGTH_SHORT).show()

        finish()
    }

    fun onAddNew(view: View) {
        startActivity(Intent(this, AddMedicationActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        binding.horizontalScrollView.adapter?.notifyDataSetChanged()
    }
}