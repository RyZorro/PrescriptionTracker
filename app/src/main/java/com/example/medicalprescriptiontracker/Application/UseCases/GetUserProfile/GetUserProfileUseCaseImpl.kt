package com.example.medicalprescriptiontracker.Application.UseCases.GetUserProfile

import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseUserProfileRepository
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserProfile

/**
 * Implementation of the [GetUserProfileUseCase] interface.
 * This class handles the use case for retrieving user profile information.
 *
 * @param repository The repository responsible for accessing user profile data.
 */
class GetUserProfileUseCaseImpl(
    private val repository: FirebaseUserProfileRepository
) : GetUserProfileUseCase {

    /**
     * Retrieves user profile information for the specified user using the injected repository.
     *
     * @param userId The unique identifier of the user.
     * @return [UserProfile] object containing user profile information.
     */
    override suspend fun getUserProfileInfo(userId: String): UserProfile {
        return repository.getUserProfileInfo(userId)
    }
}