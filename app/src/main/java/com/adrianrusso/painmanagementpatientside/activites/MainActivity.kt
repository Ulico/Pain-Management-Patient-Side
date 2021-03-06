package com.adrianrusso.painmanagementpatientside.activites

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.adrianrusso.painmanagementpatientside.databinding.ActivityMainBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import java.lang.NullPointerException

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


                try {
                    AppUser.loadInfo(user!!)
                    startActivity(Intent(this, NavigationActivity::class.java))
                } catch (e: NullPointerException) {
                    startActivity(Intent(this, CreateAccountActivity::class.java))
                }


//                val mongoClient: MongoClient =
//                    user?.getMongoClient("mongodb-atlas")!! // service for MongoDB Atlas cluster containing custom user data
//                val mongoDatabase: MongoDatabase =
//                    mongoClient.getDatabase("pain-management-database")!!
//                val mongoCollection: MongoCollection<Document> =
//                    mongoDatabase.getCollection("users-info")!!
//                mongoCollection.insertOne(
//                    Document("_id", user!!.id).append(
//                        "medications",
//                        listOf(
//                            Document.parse(
//                                Gson().toJson(
//                                    Medication(
//                                        "name1",
//                                        "purpose1",
//                                        "dose1",
//                                        "instruction1"
//                                    )
//                                )
//                            ),
//                            Document.parse(
//                                Gson().toJson(
//                                    Medication(
//                                        "name2",
//                                        "purpose2",
//                                        "dose2",
//                                        "instruction2"
//                                    )
//                                )
//                            )
//                        )
//                    )
//                )
//                    .getAsync { result ->
//                        if (result.isSuccess) {
//                            Log.v(
//                                "EXAMPLE",
//                                "Inserted custom user data document. _id of inserted document: ${result.get().insertedId}"
//                            )
//
//                        } else {
//                            Log.e(
//                                "EXAMPLE",
//                                "Unable to insert custom user data. Error: ${result.error}"
//                            )
//                        }
//                    }



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

    override fun onBackPressed() {
        return
    }

    fun onSignUp(view: View) {
        startActivity(Intent(this, SignUpActivity::class.java))
    }
}