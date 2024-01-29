package com.example.medicalprescriptiontracker.Application.UseCases.SearchAllMedication

import android.util.Log
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseMedicationRepository
import com.example.medicalprescriptiontracker.Domain.Entities.Medication

/**
 * Implementation of the [SearchAllMedicationUseCase] interface.
 * This class handles the use case for searching medications based on a provided query.
 *
 * @param medicationRepository The repository responsible for accessing medication data.
 */
class SearchAllMedicationUseCaseImpl(
    private val medicationRepository: FirebaseMedicationRepository
) : SearchAllMedicationUseCase {

    /**
     * Searches for medications in the system based on the provided search query.
     *
     * @param searchQuery The search query to filter medications.
     * @return A list of [Medication] objects matching the search query.
     */
    override suspend fun searchAllMedication(searchQuery: String): List<Medication> {
        return try {
            medicationRepository.searchAllMedication(searchQuery)
        } catch (e: Exception) {
            // Log any errors and return an empty list in case of an exception
            Log.e("SearchAllMedicationUseCaseImpl", "Error searching medications", e)
            emptyList()
        }
    }
}