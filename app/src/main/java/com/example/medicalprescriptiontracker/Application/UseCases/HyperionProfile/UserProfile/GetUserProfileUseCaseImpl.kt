package com.example.medicalprescriptiontracker.Application.UseCases.HyperionProfile.UserProfile

import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseCloudFirestoreRepository
import com.example.medicalprescriptiontracker.UserProfile

class GetUserProfileUseCaseImpl(
    private val repository: FirebaseCloudFirestoreRepository
): GetUserProfileUseCase {

    override suspend fun getUserProfileInfo(userId: String): UserProfile {
        return repository.getUserProfileInfo(userId)
    }
}