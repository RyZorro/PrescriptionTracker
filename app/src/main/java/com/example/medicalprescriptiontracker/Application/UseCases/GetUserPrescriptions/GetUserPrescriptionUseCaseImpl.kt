package com.example.medicalprescriptiontracker.Application.UseCases.GetUserPrescriptions

import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseUserPrescriptionRepository
import com.example.medicalprescriptiontracker.Domain.Entities.Prescription

class GetUserPrescriptionUseCaseImpl(
    private val repository: FirebaseUserPrescriptionRepository
) : GetUserPrescriptionUseCase {

    override suspend fun getPrescriptions(userId: String): List<Prescription> {
        return repository.getUserPrescriptions(userId)
    }
}