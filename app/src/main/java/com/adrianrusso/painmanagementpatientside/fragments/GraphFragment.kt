package com.adrianrusso.painmanagementpatientside.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adrianrusso.painmanagementpatientside.activites.WebAppInterface
import com.adrianrusso.painmanagementpatientside.databinding.FragmentScrollingGraphBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser

class GraphFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentScrollingGraphBinding.inflate(inflater, container, false)

        binding.patientGraph.settings.domStorageEnabled = true
        binding.patientGraph.settings.javaScriptEnabled = true
        binding.patientGraph.loadUrl("file:///android_asset/graph.html")
        binding.patientGraph.addJavascriptInterface(WebAppInterface(this), "Android")

        binding.patientGraph2.settings.domStorageEnabled = true
        binding.patientGraph2.settings.javaScriptEnabled = true
        binding.patientGraph2.loadUrl("file:///android_asset/moodLevel.html")
        binding.patientGraph2.addJavascriptInterface(WebAppInterface(this), "Android")

        binding.patientGraph3.settings.domStorageEnabled = true
        binding.patientGraph3.settings.javaScriptEnabled = true
        binding.patientGraph3.loadUrl("file:///android_asset/painDuration.html")
        binding.patientGraph3.addJavascriptInterface(WebAppInterface(this), "Android")

        binding.patientGraph4.settings.domStorageEnabled = true
        binding.patientGraph4.settings.javaScriptEnabled = true
        binding.patientGraph4.loadUrl("file:///android_asset/painTriggers.html")
        binding.patientGraph4.addJavascriptInterface(WebAppInterface(this), "Android")



        "Patient: ${AppUser.name}".also { binding.patientNameText.text = it }
        "Age: ${AppUser.age}".also { binding.patientAgeText.text = it }



        return binding.root
    }
}