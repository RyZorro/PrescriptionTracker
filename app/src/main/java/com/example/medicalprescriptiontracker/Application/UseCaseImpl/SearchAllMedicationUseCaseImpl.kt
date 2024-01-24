package com.example.medicalprescriptiontracker.Application.UseCaseImpl

import android.util.Log
import com.example.medicalprescriptiontracker.Application.UseCase.SearchAllMedicationUseCase
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseCloudFirestoreRepository
import com.example.medicalprescriptiontracker.Medication

/**
 * Implementation of the SearchAllMedicationUseCase interface.
 *
 * @param medicationRepository The repository responsible for interacting with the medication data source.
 */
class SearchAllMedicationUseCaseImpl(
    private val medicationRepository: FirebaseCloudFirestoreRepository
) : SearchAllMedicationUseCase {

    /**
     * Searches for medications based on the provided search query.
     *
     * @param searchQuery The search query to filter medications.
     * @return List of Medication objects matching the search query.
     */
    override suspend fun searchAllMedication(searchQuery: String): List<Medication> {
        return try {
            medicationRepository.searchAllMedication(searchQuery)
        } catch (e: Exception) {
            // Handle exceptions
            Log.e("SearchAllMedicationUseCaseImpl", "Error searching medications", e)
            emptyList()
        }
    }
}