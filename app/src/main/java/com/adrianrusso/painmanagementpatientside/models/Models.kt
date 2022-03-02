package com.adrianrusso.painmanagementpatientside.models

import android.util.Log
import com.google.gson.Gson
import io.realm.mongodb.User
import io.realm.mongodb.mongo.MongoClient
import io.realm.mongodb.mongo.MongoCollection
import io.realm.mongodb.mongo.MongoDatabase
import org.bson.Document

object AppUser {
    var name: String = ""
    var age: Int = 0
    val status: Boolean = false
    val providerName: String = ""
    lateinit var mdbUser: User

    fun sendSubmission(s: Submission) {
        val mongoClient: MongoClient =
            mdbUser.getMongoClient("mongodb-atlas")!! // service for MongoDB Atlas cluster containing custom user data
        val mongoDatabase: MongoDatabase =
            mongoClient.getDatabase("pain-management-database")!!
        val mongoCollection: MongoCollection<Document> =
            mongoDatabase.getCollection("users-info")!!

        mongoCollection.insertOne(
            Document.parse(Gson().toJson(s)).append("user_id", mdbUser.id)
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

data class Submission(
    val timestamp: String, val painLocations: List<String>,
    val treatments: List<String>,
    val alternativeTreatments: List<String>, val notes: String
)

data class Medication(
    var name: String,
    var purpose: String,
    var does: String,
    var instruction: String
)