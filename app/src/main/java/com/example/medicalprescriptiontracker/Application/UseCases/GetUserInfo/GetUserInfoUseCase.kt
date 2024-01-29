package com.example.medicalprescriptiontracker.Application.UseCases.GetUserInfo

import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserInfo

/**
 * Interface defining the use case for retrieving user medical information.
 */
interface GetUserInfoUseCase {
    /**
     * Retrieves medical information for the specified user.
     *
     * @param userId The unique identifier of the user.
     * @return [UserInfo] object containing user medical information.
     */
    suspend fun getMedicalInformation(userId: String): UserInfo
}