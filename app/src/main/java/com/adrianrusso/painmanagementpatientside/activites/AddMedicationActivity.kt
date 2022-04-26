package com.adrianrusso.painmanagementpatientside.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.adrianrusso.painmanagementpatientside.R
import com.adrianrusso.painmanagementpatientside.databinding.ActivityAddMedicationBinding
import com.adrianrusso.painmanagementpatientside.databinding.ActivityMainBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Medication

class AddMedicationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMedicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMedicationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun onSubmit(view: View) {

        AppUser.addMedication(
            Medication(
                binding.medicineInput.text.toString(),
                binding.doseInput.text.toString(),
                binding.instructionInput.text.toString()
            )
        )


        finish()
    }
}