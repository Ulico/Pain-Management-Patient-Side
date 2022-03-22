package com.adrianrusso.painmanagementpatientside.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
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
        binding.nameEditText.setText(AppUser.name)
        binding.nameEditButton.setOnClickListener {
            if (binding.nameEditText.isEnabled) {
                binding.nameEditText.isEnabled = false
                AppUser.name = binding.nameEditText.text.toString()
                AppUser.updateValue("name", binding.nameEditText.text.toString())
            } else {
                binding.nameEditText.isEnabled = true
            }

        }

//        binding.nameEditText.addTextChangedListener {
//            object : TextWatcher {
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                    binding.nameEditText.isEnabled = false
//                    binding.nameEditButton.visibility = View.VISIBLE
//                    Log.d("EXAMPLE", "test")
//                }
//
//                override fun afterTextChanged(p0: Editable?) {
//                    Log.d("EXAMPLE", "test2")
//                }
//
//            }
//        }

        return binding.root
    }

    fun onEdit(v: View) {

    }
}