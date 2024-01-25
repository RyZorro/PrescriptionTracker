package com.example.medicalprescriptiontracker.Application.UseCase

import com.example.medicalprescriptiontracker.Profile

/**
 * Use case interface for retrieving user profile information.
 */
interface GetUserProfileUseCase {
    suspend fun getUserProfileInfo(userId: String): Profile

}