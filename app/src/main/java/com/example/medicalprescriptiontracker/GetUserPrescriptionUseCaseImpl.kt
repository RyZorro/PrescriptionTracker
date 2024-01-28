package com.example.medicalprescriptiontracker

import com.example.medicalprescriptiontracker.GetUserPrescriptionUseCase
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseCloudFirestoreRepository
import com.example.medicalprescriptiontracker.Prescription

class GetUserPrescriptionUseCaseImpl(
    private val repository: FirebaseCloudFirestoreRepository
) : GetUserPrescriptionUseCase {

    override suspend fun getPrescriptions(userId: String): List<Prescription> {
        return repository.getPrescriptions(userId)
    }
}