package com.adrianrusso.painmanagementpatientside.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adrianrusso.painmanagementpatientside.activites.MainActivity
import com.adrianrusso.painmanagementpatientside.databinding.FragmentProfileBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.logoutButton.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    MainActivity::class.java
                )
            )
        }

        "Full Patient Name: ${AppUser.name}".also { binding.fullPatientNameText.text = it }

        "Provider Name: ${AppUser.providerName}".also { binding.providerNameText.text = it }
        "Primary pain location: ${AppUser.painLocations[0]}".also {
            binding.primaryPainLocation.text = it
        }
        "Other pain location: ${AppUser.painLocations[1]}".also { binding.otherPainLocation.text = it }
        "Other pain location: ${AppUser.painLocations[2]}".also { binding.otherPainLocation2.text = it }

        return binding.root
    }
}