package com.example.medicalprescriptiontracker.Infrastructure.Repositories

import android.util.Log
import com.example.medicalprescriptiontracker.Infrastructure.Service.UserProfileRepository
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserInfo
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserProfile
import com.google.firebase.Firebase
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

/**
 * Repository implementation for user profiles using Firebase Cloud Firestore.
 */
class FirebaseUserProfileRepository : UserProfileRepository {

    // Firebase Firestore instance
    private val firestore = Firebase.firestore

    /**
     * Retrieves user profile information for the specified user ID from Firestore.
     *
     * @param userId The unique identifier of the user.
     * @return [UserProfile] object containing user profile information.
     * @throws NoSuchElementException If no details document is found for the given user ID.
     * @throws Exception If any other exception occurs during the retrieval process.
     */
    override suspend fun getUserProfileInfo(userId: String): UserProfile {
        return getDocument("users", userId, "personal_information")
            ?: throw NoSuchElementException("No details document found for user with ID: $userId")
    }

    /**
     * Retrieves medical information for the specified user ID from Firestore.
     *
     * @param userId The unique identifier of the user.
     * @return [UserInfo] object containing medical information.
     * @throws NoSuchElementException If no details document is found for the given user ID.
     * @throws Exception If any other exception occurs during the retrieval process.
     */
    override suspend fun getMedicalInformation(userId: String): UserInfo {
        return getDocument("users", userId, "medical_information")
            ?: throw NoSuchElementException("No details document found for user with ID: $userId")
    }

    override suspend fun updateUserProfileInfo(userId: String, userProfile: UserProfile) {
        Log.d("FirebaseUserProfileRepository", "Updating user profile info for user ID: $userId")
        updateDocument("users", userId, "personal_information", userProfile)
    }

    override suspend fun updateUserProfileField(userId: String, field: String, updatedValue: String) {
        Log.d("FirebaseUserProfileRepository", "Updating $field for user ID: $userId")
        // Assuming you have a function to update the specific field in the document
        updateField("users", userId, field, updatedValue)
    }

    // Add a function to update a specific field in the document
    private suspend fun updateField(collection: String, userId: String, field: String, value: String) {
        val documentReference = firestore.collection(collection).document(userId)

        // Use Firestore's update function to update the specific field
        documentReference.update(field, value)
            .addOnSuccessListener {
                Log.d("FirebaseUserProfileRepository", "Field $field updated successfully")
            }
            .addOnFailureListener { e ->
                Log.e("FirebaseUserProfileRepository", "Error updating field $field", e)
            }
    }


    override suspend fun updateMedicalInformation(userId: String, medicalInfo: UserInfo) {
        Log.d("FirebaseUserProfileRepository", "Updating medical information for user ID: $userId")
        updateDocument("users", userId, "medical_information", medicalInfo)
    }


    private suspend inline fun <reified T : Any> updateDocument(
        collectionPath: String,
        userId: String,
        subCollection: String,
        data: T
    ) {
        try {
            firestore.collection(collectionPath)
                .document(userId)
                .collection(subCollection)
                .document("JacksInfo") // Use the specific custom document name
                .set(data, SetOptions.merge()) // Use SetOptions.merge() to update only specified fields
                .await()

            Log.d("FirebaseUserProfileRepository", "Document updated successfully")
        } catch (e: Exception) {
            Log.e("FirebaseUserProfileRepository", "Exception during document update: $e")
        }
    }

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
        Log.e("FirebaseUserProfileRepository", "Exception: $e")
        null
    }
}