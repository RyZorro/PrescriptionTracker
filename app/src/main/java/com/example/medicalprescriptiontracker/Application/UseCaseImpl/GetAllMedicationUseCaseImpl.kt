package com.example.medicalprescriptiontracker.Application.UseCaseImpl

import com.example.medicalprescriptiontracker.Application.UseCase.GetAllMedicationUseCase
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseCloudFirestoreRepository
import com.example.medicalprescriptiontracker.Medication

/**
 * Implementation of the GetAllMedicationUseCase interface.
 *
 * @param repository The repository responsible for interacting with the data source.
 */
class GetAllMedicationUseCaseImpl(
    private val repository: FirebaseCloudFirestoreRepository
) : GetAllMedicationUseCase {

    /**
     * Retrieves a list of all medications from the repository.
     *
     * @return List of Medication objects representing all medications.
     */
    override suspend fun getAllMedication(): List<Medication> {
        return repository.getAllMedication()
    }
}