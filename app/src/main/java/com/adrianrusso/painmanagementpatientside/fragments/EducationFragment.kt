package com.adrianrusso.painmanagementpatientside.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adrianrusso.painmanagementpatientside.activites.LearnPage1
import com.adrianrusso.painmanagementpatientside.activites.LearnPage2
import com.adrianrusso.painmanagementpatientside.activites.LearnPage3
import com.adrianrusso.painmanagementpatientside.activites.LearnPage4
import com.adrianrusso.painmanagementpatientside.databinding.FragmentEducationBinding

class EducationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEducationBinding.inflate(inflater, container, false)

        binding.button1.setOnClickListener { startActivity(Intent(activity, LearnPage1::class.java)) }
        binding.button2.setOnClickListener { startActivity(Intent(activity, LearnPage2::class.java)) }
        binding.button3.setOnClickListener { startActivity(Intent(activity, LearnPage3::class.java)) }
        binding.button4.setOnClickListener { startActivity(Intent(activity, LearnPage4::class.java)) }


        return binding.root
    }
}