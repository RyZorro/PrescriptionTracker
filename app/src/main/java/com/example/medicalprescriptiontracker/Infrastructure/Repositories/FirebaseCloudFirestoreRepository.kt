package com.example.medicalprescriptiontracker.Infrastructure.Repositories

import android.util.Log
import com.example.medicalprescriptiontracker.Infrastructure.Service.FirebaseCloudFirestore
import com.example.medicalprescriptiontracker.Prescription
import com.example.medicalprescriptiontracker.Medication
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

/**
 * Implementation of the FirebaseCloudFirestore interface, providing data operations.
 */
class FirebaseCloudFirestoreRepository: FirebaseCloudFirestore {

    private val firestore = Firebase.firestore

    /**
     * Retrieves all medications from Firebase Cloud Firestore.
     *
     * @return A list of [Medication] objects.
     */
    override suspend fun getAllMedication(): List<Medication> = try {
        firestore.collection("medication")
            .get()
            .await()
            .documents.mapNotNull { it.toObject(Medication::class.java) }
    } catch (e: Exception) {
        Log.e("FirebaseCloudFirestoreRepository", "Exception: $e")
        emptyList()
    }

    /**
     * Searches for medications in Firebase Cloud Firestore based on the provided search query.
     *
     * @param searchQuery The search query to filter medications.
     * @return A list of [Medication] objects matching the search query.
     */
    override suspend fun searchAllMedication(searchQuery: String): List<Medication> = try {
        firestore.collection("medication")
            .get()
            .await()
            .documents.mapNotNull { it.toObject(Medication::class.java) }
            .filter { it.medicationName.contains(searchQuery, ignoreCase = true) }
    } catch (e: Exception) {
        Log.e("FirebaseCloudFirestoreRepository", "Exception: $e")
        emptyList()
    }

    /**
     * Adds a medication to the user's prescriptions in Firebase Cloud Firestore.
     *
     * @param userId The unique identifier of the user.
     * @param medication The medication to be added.
     */
    override suspend fun addMedicationToUserPrescriptions(userId: String, medication: Medication) {
        try {
            firestore
                .collection("users")
                .document(userId)
                .collection("prescriptions")
                .add(
                    Prescription(
                        prescriptionId = "",  // Implement a method to generate a unique ID
                        medicationName = medication.medicationName,
                        instructions = medication.specialInstructions,  // You may customize this based on your requirements
                        dosage = medication.dosage,       // You may customize this based on your requirements
                        createdAt = ""  // Implement a method to get the current timestamp
                    )
                ).await()
        } catch (e: FirebaseFirestoreException) {
            // Handle Firestore specific exceptions
            Log.e("FirebaseFirestoreRepository", "Firestore Exception", e)
        } catch (e: Exception) {
            // Handle other exceptions
            Log.e("FirebaseFirestoreRepository", "Unknown Exception", e)
        }
    }

























    /**
     * Retrieves a list of prescriptions for a specific user from the Firestore database.
     * @param userId The unique identifier of the user.
     * @return A list of Prescription objects.
     */
    override suspend fun getPrescriptions(userId: String): List<Prescription> {
        // Reference to the collection of prescriptions for a specific user
        val prescriptionsCollection = firestore
            .collection("users")
            .document(userId)
            .collection("prescriptions")

        return try {
            // Perform an asynchronous query to get the documents in the collection
            val querySnapshot = prescriptionsCollection.get().await()
            // Map the documents to Prescription objects, filtering out null values
            querySnapshot.documents.mapNotNull { document ->
                // Ensure that toObject doesn't return null
                document.toObject(Prescription::class.java)
            }
        } catch (e: FirebaseFirestoreException) {
            // Handle Firestore specific exceptions
            Log.e("FirebaseFirestoreRepository", "Firestore Exception", e)
            emptyList()
        } catch (e: Exception) {
            // Handle other exceptions
            Log.e("FirebaseFirestoreRepository", "Unknown Exception", e)
            emptyList()
        }
    }
}

/*
override suspend fun getPrescriptionById(
    userId: String?,
    prescriptionId: String
): Prescription? {
    println("Get Prescription By Id - Start")

    // Reference to the collection of prescriptions for a specific user
    val prescriptionsCollection = userId?.let {
        firestore
            .collection("users")
            .document(it)
            .collection("prescriptions")
    }

    // Query for the prescription with the specified prescriptionId
    val query = prescriptionsCollection?.whereEqualTo("prescriptionId", prescriptionId)

    println("Firestore Query: ${query}")

    return try {
        // Perform an asynchronous query to get the specific document
        val querySnapshot = query?.get()?.await()

        // Check if any documents match the query
        if (querySnapshot != null && !querySnapshot.isEmpty) {
            // Assuming prescriptionId is unique, use the first document found
            val prescription = querySnapshot.documents[0].toObject(Prescription::class.java)
            println("Prescription found: $prescription")
            println("Get Prescription By Id - End")
            prescription
        } else {
            // Document doesn't exist
            println("Prescription not found")
            null
        }
    } catch (e: FirebaseFirestoreException) {
        // Handle Firestore specific exceptions
        println("Firestore Exception: $e")
        null
    } catch (e: Exception) {
        // Handle other exceptions
        println("Unknown Exception: $e")
        null
    }
}*/
