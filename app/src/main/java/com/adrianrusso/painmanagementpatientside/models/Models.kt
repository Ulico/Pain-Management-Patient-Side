package com.adrianrusso.painmanagementpatientside.models

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import io.realm.mongodb.User
import io.realm.mongodb.mongo.MongoClient
import io.realm.mongodb.mongo.MongoCollection
import io.realm.mongodb.mongo.MongoDatabase
import org.bson.Document
import java.time.Instant
import java.time.format.DateTimeFormatter

object AppUser {
    var name: String = ""
    var age: Int = 0
    val status: Boolean = false
    val providerName: String = ""
    lateinit var mdbUser: User

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendSubmission(s: Submission) {
        val mongoClient: MongoClient =
            mdbUser.getMongoClient("mongodb-atlas")!! // service for MongoDB Atlas cluster containing custom user data
        val mongoDatabase: MongoDatabase =
            mongoClient.getDatabase("pain-management-database")!!
        val mongoCollection: MongoCollection<Document> =
            mongoDatabase.getCollection("users-info")!!

        mongoCollection.insertOne(
            Document.parse(Gson().toJson(s)).append("user_id", mdbUser.id).append(
                "timestamp",
                DateTimeFormatter.ISO_INSTANT.format(
                    Instant.now()
                ),
            )
        ).getAsync { result ->
            if (result.isSuccess) {
                Log.v(
                    "Models",
                    "Inserted custom user data document. _id of inserted document: ${result.get().insertedId}"
                )

            } else {
                Log.e(
                    "Models",
                    "Unable to insert custom user data. Error: ${result.error}"
                )
            }
        }
    }


}

class Submission {
    var timestamp: String? = null
    var painLocations: List<String>? = null
    var treatments: List<String>? = null
    var alternativeTreatments: List<String>? = null
    var notes: String? = null
    var painLevel: Int? = null
    var feelLevel: Int? = null
    var painCoordinates: Pair<Float, Float>? = null
}

data class Medication(
    var name: String,
    var purpose: String,
    var does: String,
    var instruction: String
)