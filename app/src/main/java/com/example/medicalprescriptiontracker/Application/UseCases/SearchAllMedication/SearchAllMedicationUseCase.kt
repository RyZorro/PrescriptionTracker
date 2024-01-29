package com.example.medicalprescriptiontracker.Application.UseCases.SearchAllMedication

import com.example.medicalprescriptiontracker.Domain.Entities.Medication

/**
 * Interface defining the use case for searching medications based on a provided query.
 */
interface SearchAllMedicationUseCase {
    /**
     * Searches for medications in the system based on the provided search query.
     *
     * @param searchQuery The search query to filter medications.
     * @return A list of [Medication] objects matching the search query.
     */
    suspend fun searchAllMedication(searchQuery: String): List<Medication>
}