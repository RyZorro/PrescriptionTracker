package com.example.medicalprescriptiontracker.Application.UseCases.GetAllMedication

import com.example.medicalprescriptiontracker.Domain.Entities.Medication

/**
 * Interface defining the use case for retrieving all medications.
 */
interface GetAllMedicationUseCase {
    /**
     * Retrieves a list of all medications.
     *
     * @return A list of [Medication] objects.
     */
    suspend fun getAllMedication(): List<Medication>
}