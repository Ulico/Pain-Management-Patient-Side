package com.adrianrusso.painmanagementpatientside.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adrianrusso.painmanagementpatientside.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}