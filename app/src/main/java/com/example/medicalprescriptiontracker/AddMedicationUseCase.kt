package com.example.medicalprescriptiontracker

/**
 * Use case interface for adding Medication Objects.
 */
interface AddMedicationUseCase {
    suspend fun addMedicationToUserPrescriptions(userId: String, medication: Medication)
}