package com.example.medicalprescriptiontracker

import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserInfo
import com.example.medicalprescriptiontracker.Infrastructure.Service.UserProfileRepository

interface UpdateUserMedicalInfoUseCase {
    suspend fun updateMedicalInformation(userId: String, medicalInfo: UserInfo)
}