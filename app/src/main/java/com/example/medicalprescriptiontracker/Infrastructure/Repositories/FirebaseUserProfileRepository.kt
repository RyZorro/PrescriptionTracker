package com.example.medicalprescriptiontracker.Infrastructure.Repositories

import android.util.Log
import com.example.medicalprescriptiontracker.Infrastructure.Service.UserProfileRepository
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserInfo
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserProfile
import com.google.firebase.Firebase
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
        return getDocument("users", userId, "Personal_Information")
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
        return getDocument("users", userId, "Medical_Information")
            ?: throw NoSuchElementException("No details document found for user with ID: $userId")
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