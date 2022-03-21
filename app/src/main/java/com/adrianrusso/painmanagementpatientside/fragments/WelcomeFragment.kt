package com.adrianrusso.painmanagementpatientside.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adrianrusso.painmanagementpatientside.activites.AddMedicationActivity
import com.adrianrusso.painmanagementpatientside.activites.CheckinActivity
import com.adrianrusso.painmanagementpatientside.databinding.FragmentWelcomeBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        "Welcome back, ${AppUser.name.substringBefore(" ")}".also { binding.welcomeText.text = it }


        binding.completeCheckInButton.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    CheckinActivity::class.java
                )
            )
        }

        binding.addTreatmentButton.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    AddMedicationActivity::class.java
                )
            )
        }

        return binding.root
    }

}