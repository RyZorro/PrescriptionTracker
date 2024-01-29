package com.example.medicalprescriptiontracker

import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserInfo
import com.example.medicalprescriptiontracker.Infrastructure.Service.UserProfileRepository

class UpdateUserMedicalInfoUseCaseImpl(private val repository: UserProfileRepository): UpdateUserMedicalInfoUseCase {
    override suspend fun updateMedicalInformation(userId: String, medicalInfo: UserInfo) {
        // Additional business logic or validation can be added here before updating
        repository.updateMedicalInformation(userId, medicalInfo)
    }
}