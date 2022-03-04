package com.adrianrusso.painmanagementpatientside.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adrianrusso.painmanagementpatientside.databinding.FragmentGraphBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser

class GraphFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGraphBinding.inflate(inflater, container, false)

        "Patient: ${AppUser.name}".also { binding.patientNameText.text = it }
        "Age: ${AppUser.age}".also { binding.patientAgeText.text = it }

        "Most common pain location:\n${AppUser.painLocations[0]}".also {
            binding.painLocationText.text = it
        }
        "Most common treatment used:\n${AppUser.commonTreatments[0]}".also {
            binding.commonTreatmentText.text = it
        }
        "Most alternative treatment used:\n${AppUser.alternativeTreatments[0]}".also {
            binding.alternativeTreatmentText.text = it
        }
        "Notes:\n${AppUser.notes}".also { binding.notesText.text = it }


        return binding.root
    }
}