package com.adrianrusso.painmanagementpatientside.activites

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.adrianrusso.painmanagementpatientside.R
import com.adrianrusso.painmanagementpatientside.databinding.ActivityExternalFactorsScrollingBinding

class ExternalFactorsScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExternalFactorsScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExternalFactorsScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
    }
}