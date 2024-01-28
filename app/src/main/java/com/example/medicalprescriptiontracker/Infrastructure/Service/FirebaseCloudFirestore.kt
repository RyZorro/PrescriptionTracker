package com.example.medicalprescriptiontracker.Infrastructure.Service

import com.example.medicalprescriptiontracker.UserInfo
import com.example.medicalprescriptiontracker.Prescription
import com.example.medicalprescriptiontracker.Medication
import com.example.medicalprescriptiontracker.UserProfile

/**
 * Interface defining data operations related to medications using Firebase Cloud Firestore.
 */
interface FirebaseCloudFirestore {

    /**
     * Retrieves all medications from Firebase Cloud Firestore.
     *
     * @return A list of [Medication] objects.
     */
    suspend fun getAllMedication(): List<Medication>

    /**
     * Searches for medications in Firebase Cloud Firestore based on the provided search query.
     *
     * @param searchQuery The search query to filter medications.
     * @return A list of [Medication] objects matching the search query.
     */
    suspend fun searchAllMedication(searchQuery: String): List<Medication>

    /**
     * Adds a medication to the user's prescriptions in Firebase Cloud Firestore.
     *
     * @param userId The unique identifier of the user.
     * @param medication The [Medication] to be added to the user's prescriptions.
     */
    suspend fun addMedicationToUserPrescriptions(userId: String, medication: Medication)

    /**
     * Retrieves a list of prescriptions for a specific user from Firebase Cloud Firestore.
     *
     * @param userId The unique identifier of the user.
     * @return A list of [Prescription] objects.
     */
    suspend fun getPrescriptions(userId: String): List<Prescription>

    /**
     * Retrieve user profile information for the specified user ID.
     *
     * @param userId The unique identifier of the user.
     * @return [UserProfile] object containing user profile information.
     */
    suspend fun getUserProfileInfo(userId: String): UserProfile

    suspend fun getMedicalInformation(userId: String): UserInfo

}