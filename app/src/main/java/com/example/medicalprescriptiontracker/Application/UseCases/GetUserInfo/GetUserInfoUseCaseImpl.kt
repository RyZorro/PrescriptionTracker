package com.example.medicalprescriptiontracker.Application.UseCases.GetUserInfo

import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserInfo
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseUserProfileRepository

/**
 * Implementation of the [GetUserInfoUseCase] interface.
 * This class handles the use case for retrieving user medical information.
 *
 * @param repository The repository responsible for accessing user profile data.
 */
class GetUserInfoUseCaseImpl(private val repository: FirebaseUserProfileRepository) :
    GetUserInfoUseCase {

    /**
     * Retrieves medical information for the specified user using the injected repository.
     *
     * @param userId The unique identifier of the user.
     * @return [UserInfo] object containing user medical information.
     */
    override suspend fun getMedicalInformation(userId: String): UserInfo {
        return repository.getMedicalInformation(userId)
    }
}