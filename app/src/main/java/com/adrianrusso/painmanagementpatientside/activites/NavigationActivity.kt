package com.adrianrusso.painmanagementpatientside.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.adrianrusso.painmanagementpatientside.R
import com.adrianrusso.painmanagementpatientside.databinding.ActivityNavigationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigationView

        val navController =
            findNavController(R.id.navigationFragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigationHome,
                R.id.navigationInsights,
                R.id.navigationTips,
                R.id.navigationPlay,
                R.id.navigationSettings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}