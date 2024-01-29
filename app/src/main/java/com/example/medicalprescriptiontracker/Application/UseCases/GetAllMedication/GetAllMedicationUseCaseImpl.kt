package com.example.medicalprescriptiontracker.Application.UseCases.GetAllMedication

import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseMedicationRepository
import com.example.medicalprescriptiontracker.Domain.Entities.Medication

/**
 * Implementation of the [GetAllMedicationUseCase] interface.
 * This class handles the use case for retrieving all medications.
 *
 * @param repository The repository responsible for accessing medication data.
 */
class GetAllMedicationUseCaseImpl(
    private val repository: FirebaseMedicationRepository
) : GetAllMedicationUseCase {

    /**
     * Retrieves a list of all medications using the injected repository.
     *
     * @return A list of [Medication] objects.
     */
    override suspend fun getAllMedication(): List<Medication> {
        return repository.getAllMedication()
    }
}