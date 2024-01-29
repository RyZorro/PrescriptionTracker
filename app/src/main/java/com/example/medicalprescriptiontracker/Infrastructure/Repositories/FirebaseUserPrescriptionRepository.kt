package com.example.medicalprescriptiontracker.Infrastructure.Repositories

import com.example.medicalprescriptiontracker.Infrastructure.Service.UserPrescriptionRepository
import com.example.medicalprescriptiontracker.Domain.Entities.Prescription
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

/**
 * Implementation of the UserPrescriptionRepository interface.
 */
class FirebaseUserPrescriptionRepository : UserPrescriptionRepository {

    private val firestore = Firebase.firestore

    /**
     * Retrieves all prescriptions for the specified user ID from Firestore.
     *
     * @param userId The unique identifier of the user.
     * @return A list of [Prescription] objects representing the user's prescriptions.
     */
    override suspend fun getUserPrescriptions(userId: String): List<Prescription> = try {
        firestore.collection("users")
            .document(userId)
            .collection("prescriptions")
            .whereEqualTo("status", "accepted")  // Adjust the status value as needed
            .get()
            .await()
            .documents.mapNotNull { it.toObject(Prescription::class.java) }
    } catch (e: FirebaseFirestoreException) {
        emptyList()
    } catch (e: Exception) {
        emptyList()
    }
}