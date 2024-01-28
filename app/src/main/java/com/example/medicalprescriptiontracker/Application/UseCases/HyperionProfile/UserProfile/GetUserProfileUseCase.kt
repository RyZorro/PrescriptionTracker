package com.example.medicalprescriptiontracker.Application.UseCases.HyperionProfile.UserProfile

import com.example.medicalprescriptiontracker.UserProfile

interface GetUserProfileUseCase {
    suspend fun getUserProfileInfo(userId: String): UserProfile

}