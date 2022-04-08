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
    var status: Boolean = false
    var providerName: String = ""
    var medications: MutableList<Medication> = mutableListOf()
    var painLocations: List<String> = mutableListOf()
    var commonTreatments: List<String> = mutableListOf()
    var alternativeTreatments: List<String> = mutableListOf()
    var activities: List<String> = mutableListOf()
    var notes: String = ""

    lateinit var mdbUser: User

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendSubmission(s: Submission, isPain: Boolean) {
        val mongoClient: MongoClient =
            mdbUser.getMongoClient("mongodb-atlas")!! // service for MongoDB Atlas cluster containing custom user data
        val mongoDatabase: MongoDatabase =
            mongoClient.getDatabase("pain-management-database")!!
        val mongoCollection: MongoCollection<Document> =
            mongoDatabase.getCollection("users-info")!!
        Log.d("TEST", s.painDescriptors.toString())
        val document = Document.parse(Gson().toJson(s)).append("user_id", mdbUser.id).append(
            "timestamp",
            DateTimeFormatter.ISO_INSTANT.format(
                Instant.now()
            ),
        ).append("name", name).append("pain_doc", isPain)
        mongoCollection.insertOne(
            document

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

    @Suppress("UNCHECKED_CAST")
    fun loadInfo(user: User) {
        mdbUser = user
//        val mongoClient: MongoClient =
//            user.getMongoClient("mongodb-atlas")!! // service for MongoDB Atlas cluster containing custom user data
//        val mongoDatabase: MongoDatabase =
//            mongoClient.getDatabase("pain-management-database")!!
//        val mongoCollection: MongoCollection<Document> =
//            mongoDatabase.getCollection("users-info")!!
        val customUserData: Document? = user.customData

        if (customUserData == null) {
            throw NullPointerException()
        } else {
//        name = customUserData["name"] as String
            Log.d("Models", customUserData.toString())

            try {
                name = customUserData["name"] as String


            } catch (e: NullPointerException) {
                throw NullPointerException()
            }

            try {
                painLocations = customUserData["pain_locations"] as List<String>
                age = customUserData["age"] as Int
                providerName = customUserData["provider_name"] as String

                medications = (customUserData["medications"] as MutableList<Document>).map {
                    Medication(
                        it["name"] as String, it["purpose"] as String, it["dose"] as String,
                        it["instruction"] as String
                    )
                }.toMutableList()


                status = customUserData["status"] as Boolean
                commonTreatments = customUserData["common_treatments"] as List<String>
                alternativeTreatments = customUserData["alternative_treatments"] as List<String>
                activities = customUserData["activites"] as List<String>
                notes = customUserData["notes"] as String
            } catch (e: NullPointerException) {

            }
        }
//        for (medication in medicationList) {
//            mongoCollection.findOne(Document("_id", patientId))
//                .getAsync { task ->
//                    if (task.isSuccess) {
//                        val result = task.get()
////                        Log.d("AppData", result.toString())
////                        val p = Gson().fromJson(result.toJson(), Patient::class.java)
////                        Log.d("AppData", p.toString())
//                        patients.add(
//
//                            Patient(
//                                result["name"] as String,
//                                result["age"] as Int,
//                                result["status"] as Boolean,
//                                result["provider_name"] as String,
//                                result["pain_locations"] as List<String>,
//                                result["treatments"] as List<String>,
//                                result["alternative_treatments"] as List<String>,
//                                result["notes"] as String
//
//                            )
//                        )
//                    } else {
//                        Log.e("AppUser", "failed to find document with: ${task.error}")
//                    }
//                }
//        }
    }

    fun firstTimeSetup() {
        val mongoClient: MongoClient =
            mdbUser.getMongoClient("mongodb-atlas")!! // service for MongoDB Atlas cluster containing custom user data
        val mongoDatabase: MongoDatabase =
            mongoClient.getDatabase("pain-management-database")!!
        val mongoCollection: MongoCollection<Document> =
            mongoDatabase.getCollection("users-info")!!
        mongoCollection.insertOne(
            Document("_id", mdbUser.id.toString()).append("name", name)
                .append("provider_name", providerName)
                .append("pain_locations", painLocations),
        ).getAsync { result ->
            if (result.isSuccess) {
                Log.v("EXAMPLE", "Inserted custom user data document.")

            } else {
                Log.e("EXAMPLE", "Unable to update custom user data. Error: ${result.error}")
            }
        }
    }

    fun addMedication(m: Medication) {
        medications.add(m)
        val mongoClient: MongoClient =
            mdbUser.getMongoClient("mongodb-atlas")!! // service for MongoDB Atlas cluster containing custom user data
        val mongoDatabase: MongoDatabase =
            mongoClient.getDatabase("pain-management-database")!!
        val mongoCollection: MongoCollection<Document> =
            mongoDatabase.getCollection("users-info")!!
        mongoCollection.updateOne(
            Document("_id", mdbUser.id),
            Document(
                "\$set",
                Document("medications", medications.map { Document.parse(Gson().toJson(it)) })
            )
        ).getAsync { result ->
            if (result.isSuccess) {
                if (result.get().modifiedCount == 1L) {
                    Log.v("EXAMPLE", "Updated custom user data document.")
                } else {
                    Log.v("EXAMPLE", "Could not find custom user data document to update.")
                }
            } else {
                Log.e("EXAMPLE", "Unable to update custom user data. Error: ${result.error}")
            }
        }
    }

    fun updateValue(key: String, value: String) {
        val mongoClient: MongoClient =
            mdbUser.getMongoClient("mongodb-atlas")!! // service for MongoDB Atlas cluster containing custom user data
        val mongoDatabase: MongoDatabase =
            mongoClient.getDatabase("pain-management-database")!!
        val mongoCollection: MongoCollection<Document> =
            mongoDatabase.getCollection("users-info")!!
        mongoCollection.updateOne(
            Document("_id", mdbUser.id),
            Document(
                "\$set",
                Document(key, value)
            )
        ).getAsync { result ->
            if (result.isSuccess) {
                if (result.get().modifiedCount == 1L) {
                    Log.v("EXAMPLE", "Updated custom user data document.")
                } else {
                    Log.v("EXAMPLE", "Could not find custom user data document to update.")
                }
            } else {
                Log.e("EXAMPLE", "Unable to update custom user data. Error: ${result.error}")
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
    var activity: String? = null
    var painCoordinates: List<Pair<Float, Float>>? = null
    var painInterferenceWithGeneralActivities: Int? = null
    var painInterferenceWithEnjoyment: Int? = null
    var painDescriptors: List<String>? = null
    var painTriggers: List<String>? = null
    var painDuration: Int? = null
    var sleepDuration: Int? = null
    var sleepQuality: Int? = null
    var exerciseDuration: Int? = null
    var exerciseIntensity: Int? = null
    var moodLevel: Int? = null
    var bowelMovement: Boolean? = null
    var medicationTaken: Boolean? = null
    var nonMedicationStrategies: List<String>? = null
    var dietAmount: Int? = null
    var painTimeOfDay: List<String>? = null
    var painLocationOfPatient: List<String>? = null
    var painOtherSymptoms: List<String>? = null
    var finishedMedications: Boolean? = null
}

data class Medication(
    var name: String,
    var purpose: String,
    var dose: String,
    var instruction: String
)

