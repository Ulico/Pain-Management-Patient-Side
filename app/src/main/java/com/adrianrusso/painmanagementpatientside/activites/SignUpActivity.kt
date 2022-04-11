package com.adrianrusso.painmanagementpatientside.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.adrianrusso.painmanagementpatientside.R
import com.adrianrusso.painmanagementpatientside.databinding.ActivityMainBinding
import com.adrianrusso.painmanagementpatientside.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun onSubmit(view: View) {
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()
        val confirmPassword = binding.confirmPassword.text.toString()
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Snackbar.make(
                binding.root,
                "Please enter your username and password.",
                Snackbar.LENGTH_SHORT
            ).show()
        } else if (password != confirmPassword) {
            Snackbar.make(
                binding.root,
                "Passwords do not match.",
                Snackbar.LENGTH_SHORT
            ).show()
        } else {

            taskApp.emailPassword.registerUserAsync(username, password) {
                if (it.isSuccess) {
                    Log.i("EXAMPLE", "Successfully registered user.")
                    finish()
                } else {
                    Snackbar.make(
                        binding.root,
                        "Failed to register user: ${it.error}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

        }

    }
}