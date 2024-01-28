package com.example.medicalprescriptiontracker.Application.UseCases.HyperionProfile.UserInfo

import com.example.medicalprescriptiontracker.Application.UseCases.HyperionProfile.UserInfo.GetUserInfoUseCase
import com.example.medicalprescriptiontracker.UserInfo
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseCloudFirestoreRepository

class GetUserInfoUseCaseImpl(private val repository: FirebaseCloudFirestoreRepository) :
    GetUserInfoUseCase {

    override suspend fun getMedicalInformation(userId: String): UserInfo {
        return repository.getMedicalInformation(userId)
    }
}