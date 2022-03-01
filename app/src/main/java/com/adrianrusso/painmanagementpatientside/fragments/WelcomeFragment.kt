package com.adrianrusso.painmanagementpatientside.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adrianrusso.painmanagementpatientside.activites.CheckinActivity
import com.adrianrusso.painmanagementpatientside.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentWelcomeBinding.inflate(inflater, container, false)


        binding.completeCheckInButton.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    CheckinActivity::class.java
                )
            )
        }

        return binding.root
    }

}