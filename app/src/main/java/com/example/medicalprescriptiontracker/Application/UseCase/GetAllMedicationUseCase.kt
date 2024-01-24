package com.example.medicalprescriptiontracker.Application.UseCase

import com.example.medicalprescriptiontracker.Medication

/**
 * Use case interface for retrieving a list of all medications.
 */
interface GetAllMedicationUseCase {
    suspend fun getAllMedication(): List<Medication>
}