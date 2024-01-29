package com.example.medicalprescriptiontracker

import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserProfile
import com.example.medicalprescriptiontracker.Infrastructure.Service.UserProfileRepository

class UpdateUserProfileUseCaseImpl(private val repository: UserProfileRepository): UpdateUserProfileUseCase {
    override suspend fun updateUserProfileInfo(userId: String, userProfile: UserProfile) {
        // Additional business logic or validation can be added here before updating
        repository.updateUserProfileInfo(userId, userProfile)
    }

    override suspend fun updateUserProfileField(userId: String, field: String, updatedValue: String) {
        // Additional business logic or validation can be added here before updating a specific field
        repository.updateUserProfileField(userId, field, updatedValue)
    }
}