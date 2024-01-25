package com.example.medicalprescriptiontracker.Application.UseCaseImpl

import com.example.medicalprescriptiontracker.Application.UseCase.GetUserProfileUseCase
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseCloudFirestoreRepository
import com.example.medicalprescriptiontracker.Profile

/**
 * Implementation of the GetUserProfileUseCase interface, providing the logic to retrieve user profile information.
 *
 * @param repository The repository responsible for data operations related to user profiles using Firebase Cloud Firestore.
 */
class GetUserProfileUseCaseImpl(
    private val repository: FirebaseCloudFirestoreRepository
): GetUserProfileUseCase {

    /**
     * Retrieves user profile information for the specified user ID.
     *
     * @param userId The unique identifier of the user.
     * @return A [Profile] object containing user details.
     */
    override suspend fun getUserProfileInfo(userId: String): Profile {
        return repository.getUserProfileInfo(userId)
    }
}