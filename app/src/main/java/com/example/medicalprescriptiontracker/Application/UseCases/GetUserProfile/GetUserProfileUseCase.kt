package com.example.medicalprescriptiontracker.Application.UseCases.GetUserProfile

import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserProfile

/**
 * Interface defining the use case for retrieving user profile information.
 */
interface GetUserProfileUseCase {
    /**
     * Retrieves user profile information for the specified user.
     *
     * @param userId The unique identifier of the user.
     * @return [UserProfile] object containing user profile information.
     */
    suspend fun getUserProfileInfo(userId: String): UserProfile
}