package com.example.medicalprescriptiontracker.Infrastructure.Service

import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserInfo
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserProfile

/**
 * Repository interface defining data operations related to user profiles using Firebase Cloud Firestore.
 */
interface UserProfileRepository {

    /**
     * Retrieves user profile information for the specified user ID from Firestore.
     *
     * @param userId The unique identifier of the user.
     * @return [UserProfile] object containing user profile information.
     * @throws NoSuchElementException If no details document is found for the given user ID.
     * @throws Exception If any other exception occurs during the retrieval process.
     */
    suspend fun getUserProfileInfo(userId: String): UserProfile

    /**
     * Retrieves medical information for the specified user ID from Firestore.
     *
     * @param userId The unique identifier of the user.
     * @return [UserInfo] object containing medical information.
     * @throws NoSuchElementException If no details document is found for the given user ID.
     * @throws Exception If any other exception occurs during the retrieval process.
     */
    suspend fun getMedicalInformation(userId: String): UserInfo

    suspend fun updateUserProfileInfo(userId: String, userProfile: UserProfile)
    suspend fun updateMedicalInformation(userId: String, medicalInfo: UserInfo)

    suspend fun updateUserProfileField(userId: String, field: String, updatedValue: String)
}