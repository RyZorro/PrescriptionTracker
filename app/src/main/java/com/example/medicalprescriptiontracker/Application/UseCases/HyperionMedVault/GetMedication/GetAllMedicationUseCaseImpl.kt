package com.example.medicalprescriptiontracker.Application.UseCases.HyperionMedVault.GetMedication

import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseCloudFirestoreRepository
import com.example.medicalprescriptiontracker.Medication

class GetAllMedicationUseCaseImpl(
    private val repository: FirebaseCloudFirestoreRepository
) : GetAllMedicationUseCase {

    override suspend fun getAllMedication(): List<Medication> {
        return repository.getAllMedication()
    }
}