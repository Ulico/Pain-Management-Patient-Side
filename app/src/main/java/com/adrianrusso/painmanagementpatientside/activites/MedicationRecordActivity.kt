package com.adrianrusso.painmanagementpatientside.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.adrianrusso.painmanagementpatientside.R

class MedicationRecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medication_record)
    }

    fun onSubmit(view: View) {
        startActivity(Intent(this, CheckinActivity::class.java))
    }
}