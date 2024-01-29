package com.example.medicalprescriptiontracker.Infrastructure.Service

import com.example.medicalprescriptiontracker.Domain.Entities.Medication

/**
 * Repository interface for medication-related operations.
 */
interface MedicationRepository {
    /**
     * Retrieves all medications from the repository.
     *
     * @return A list of [Medication] objects.
     */
    suspend fun getAllMedication(): List<Medication>

    /**
     * Searches for medications in the repository based on the provided search query.
     *
     * @param searchQuery The search query to filter medications.
     * @return A list of [Medication] objects matching the search query.
     */
    suspend fun searchAllMedication(searchQuery: String): List<Medication>

    /**
     * Adds a medication to the user's prescriptions in the repository.
     *
     * @param userId The unique identifier of the user.
     * @param medication The [Medication] to be added to the user's prescriptions.
     */
    suspend fun addMedicationToUserPrescriptions(userId: String, medication: Medication)
}