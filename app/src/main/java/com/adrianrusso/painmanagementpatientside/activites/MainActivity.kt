package com.adrianrusso.painmanagementpatientside.activites

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.adrianrusso.painmanagementpatientside.databinding.ActivityMainBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Submission
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import java.time.Instant
import java.time.format.DateTimeFormatter

lateinit var taskApp: App
const val appID = "test_app-svywj"

class MainActivity : AppCompatActivity() {

    var user: User? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        user = null
        binding.password.text.clear()

        Realm.init(this)
        taskApp = App(
            AppConfiguration.Builder(appID)
                .build()
        )


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onLogIn(view: View) {
        if (binding.username.text.isEmpty() || binding.password.text.isEmpty()) {
            Snackbar.make(
                binding.root,
                "Please enter your username and password.",
                Snackbar.LENGTH_SHORT
            ).show()
            return
        }


        val emailPasswordCredentials: Credentials = Credentials.emailPassword(
            binding.username.text.toString(),
            binding.password.text.toString()
        )

        taskApp.loginAsync(emailPasswordCredentials) {
            if (it.isSuccess) {
                user = taskApp.currentUser()


                AppUser.mdbUser = user!!
//        var s = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
                AppUser.sendSubmission(
                    Submission(
                        DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
                        listOf("test", "test2"),
                        listOf("test", "test2"),
                        listOf("test", "test2"),
                        "Notes"
                    )
                )


                val intent = Intent(this, TwoFactorAuthentication::class.java)
                startActivity(intent)
            } else {
                it.error.errorMessage?.let { it1 ->
                    Snackbar.make(
                        binding.root,
                        it1,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}