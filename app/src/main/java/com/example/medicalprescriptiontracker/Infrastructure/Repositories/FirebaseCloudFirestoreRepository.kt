package com.example.medicalprescriptiontracker.Infrastructure.Repositories

import android.util.Log
import com.example.medicalprescriptiontracker.UserInfo
import com.example.medicalprescriptiontracker.Infrastructure.Service.FirebaseCloudFirestore
import com.example.medicalprescriptiontracker.Prescription
import com.example.medicalprescriptiontracker.Medication
import com.example.medicalprescriptiontracker.UserProfile
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
     * Retrieves user profile information for the specified user ID from Firestore.
     *
     * @param userId The unique identifier of the user.
     * @return [UserProfile] object containing user profile information.
     * @throws NoSuchElementException If no details document is found for the given user ID.
     * @throws Exception If any other exception occurs during the retrieval process.
     */
    override suspend fun getUserProfileInfo(userId: String): UserProfile =
        getDocument("users", userId, "Personal_Information")
            ?: throw NoSuchElementException("No details document found for user with ID: $userId")


    override suspend fun getMedicalInformation(userId: String): UserInfo =
        getDocument("users", userId, "Medical_Information")
            ?: throw NoSuchElementException("No details document found for user with ID: $userId")

    /**
     * Retrieves a single document of type [T] from Firestore.
     *
     * @param collectionPath The path of the Firestore collection.
     * @param userId The unique identifier of the user.
     * @param subCollection The sub-collection within the user's documents.
     * @return The retrieved document of type [T], or null if not found.
     */
    private suspend inline fun <reified T> getDocument(
        collectionPath: String,
        userId: String,
        subCollection: String
    ): T? = try {
        firestore.collection(collectionPath)
            .document(userId)
            .collection(subCollection)
            .limit(1)
            .get()
            .await()
            .documents
            .firstOrNull()
            ?.toObject(T::class.java)
    } catch (e: Exception) {
        // Log any exceptions and return null
        Log.e("FirebaseCloudFirestoreRepository", "Exception: $e")
        null
    }

    /**
     * Retrieves a list of documents of type [T] from Firestore.
     *
     * @param collectionPath The path of the Firestore collection.
     * @return A list of documents of type [T].
     */
    private suspend inline fun <reified T> getCollection(collectionPath: String): List<T> = try {
        firestore.collection(collectionPath)
            .get()
            .await()
            .documents.mapNotNull { it.toObject(T::class.java) }
    } catch (e: Exception) {
        // Log any exceptions and return an empty list
        Log.e("FirebaseCloudFirestoreRepository", "Exception: $e")
        emptyList()
    }

    /**
     * Adds a document of type [T] to a Firestore sub-collection.
     *
     * @param collectionPath The path of the Firestore collection.
     * @param userId The unique identifier of the user.
     * @param subCollection The sub-collection within the user's documents.
     * @param document The document of type [T] to be added.
     */
    private suspend inline fun <reified T : Any> addDocument(
        collectionPath: String,
        userId: String,
        subCollection: String,
        document: T
    ) {
        try {
            firestore
                .collection(collectionPath)
                .document(userId)
                .collection(subCollection)
                .add(document)
                .await()
        } catch (e: FirebaseFirestoreException) {
            // Handle Firestore specific exceptions
            Log.e("FirebaseCloudFirestoreRepository", "Firestore Exception", e)
        } catch (e: Exception) {
            // Handle other exceptions
            Log.e("FirebaseCloudFirestoreRepository", "Unknown Exception", e)
        }
    }






























































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
                        prescriptionId = "",
                        medicationName = medication.medicationName,
                        instructions = medication.specialInstructions,
                        dosage = medication.dosage,
                        createdAt = ""
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
    override suspend fun getPrescriptions(userId: String): List<Prescription> = try {
        firestore.collection("users")
            .document(userId)
            .collection("prescriptions")
            .get()
            .await()
            .documents.mapNotNull { it.toObject(Prescription::class.java) }
    } catch (e: FirebaseFirestoreException) {
        Log.e("FirebaseFirestoreRepository", "Firestore Exception", e)
        emptyList()
    } catch (e: Exception) {
        Log.e("FirebaseFirestoreRepository", "Unknown Exception", e)
        emptyList()
    }

    /**
     * Retrieves user profile information for the specified user ID from Firestore.
     *
     * @param userId The unique identifier of the user.
     * @return [UserProfile] object containing user profile information.
     * @throws NoSuchElementException If no details document is found for the given user ID.
     * @throws Exception If any other exception occurs during the retrieval process.
     */
    /*override suspend fun getUserProfileInfo(userId: String): PersonalInformation = try {
        val userPersonalInformation = firestore
            .collection("users")
            .document(userId)
            .collection("Personal_Information")
            .limit(1)
            .get()
            .await()
            .documents
            .firstOrNull()
            ?.toObject(PersonalInformation::class.java)

        Log.d("FirebaseFirestoreRepository", "UserProfile: $userPersonalInformation")

        userPersonalInformation ?: throw NoSuchElementException("No details document found for user with ID: $userId")
    } catch (e: Exception) {
        Log.e("FirebaseFirestoreRepository", "Exception: $e")
        throw e
    }*/




    /*override suspend fun getAllMedication(): List<Medication> =
        getCollection("medication")

    override suspend fun searchAllMedication(searchQuery: String): List<Medication> =
        getCollection("medication").filter { it.medicationName.contains(searchQuery, ignoreCase = true) }

    override suspend fun addMedicationToUserPrescriptions(userId: String, medication: Medication) =
        addDocument("users", userId, "prescriptions", medication)

    override suspend fun getPrescriptions(userId: String): List<Prescription> =
        getCollection("users/$userId/prescriptions")*/
}