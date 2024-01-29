package com.example.medicalprescriptiontracker

import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserProfile
import com.example.medicalprescriptiontracker.Infrastructure.Service.UserProfileRepository

interface UpdateUserProfileUseCase {
    suspend fun updateUserProfileInfo(userId: String, userProfile: UserProfile)
    suspend fun updateUserProfileField(userId: String, field: String, updatedValue: String)
    }