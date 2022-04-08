package com.adrianrusso.painmanagementpatientside.activites

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.adrianrusso.painmanagementpatientside.databinding.ActivityCreateAccountBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun onSubmit(view: View) {
        AppUser.name = binding.nameEditText.text.toString()
        AppUser.providerName = binding.doctorNameEditText.text.toString()
        AppUser.painLocations = listOf(binding.painLocationEditText1.text.toString(), binding.painLocationEditText2.text.toString(), binding.painLocationEditText3.text.toString())
        AppUser.firstTimeSetup()
        finish()
    }
}