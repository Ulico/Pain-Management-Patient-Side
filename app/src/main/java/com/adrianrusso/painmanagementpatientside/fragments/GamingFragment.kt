package com.adrianrusso.painmanagementpatientside.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adrianrusso.painmanagementpatientside.databinding.FragmentGamingBinding

class GamingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGamingBinding.inflate(inflater, container, false)
        return binding.root
    }
}