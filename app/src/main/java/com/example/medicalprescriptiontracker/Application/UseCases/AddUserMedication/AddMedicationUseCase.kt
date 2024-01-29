package com.example.medicalprescriptiontracker.Application.UseCases.AddUserMedication

import com.example.medicalprescriptiontracker.Domain.Entities.Medication

/**
 * Use case interface for adding Medication objects to user prescriptions.
 * This interface defines a method to add a Medication object to a user's prescription list.
 */
interface AddMedicationUseCase {
    /**
     * Adds a Medication object to the user's prescription list.
     *
     * @param userId The unique identifier of the user.
     * @param medication The Medication object to be added.
     */
    suspend fun addMedicationToUserPrescriptions(userId: String, medication: Medication)
}