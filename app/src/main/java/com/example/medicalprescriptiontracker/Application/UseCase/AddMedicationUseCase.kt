package com.example.medicalprescriptiontracker.Application.UseCase

import com.example.medicalprescriptiontracker.Medication

/**
 * Use case interface for adding Medication Objects.
 */
interface AddMedicationUseCase {
    suspend fun addMedicationToUserPrescriptions(userId: String, medication: Medication)
}