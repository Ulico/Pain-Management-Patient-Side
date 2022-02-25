package com.adrianrusso.painmanagementpatientside.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adrianrusso.painmanagementpatientside.databinding.FragmentGraphBinding

class GraphFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGraphBinding.inflate(inflater, container, false)
        return binding.root
    }
}