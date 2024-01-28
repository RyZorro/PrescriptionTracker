package com.example.medicalprescriptiontracker.Application.UseCases.HyperionProfile.UserInfo

import com.example.medicalprescriptiontracker.UserInfo

interface GetUserInfoUseCase {
    suspend fun getMedicalInformation(userId: String): UserInfo
}