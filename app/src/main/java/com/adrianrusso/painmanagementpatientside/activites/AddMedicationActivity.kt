package com.adrianrusso.painmanagementpatientside.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.adrianrusso.painmanagementpatientside.R

class AddMedicationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medication)
    }

    fun onSubmit(view: View) {}
}