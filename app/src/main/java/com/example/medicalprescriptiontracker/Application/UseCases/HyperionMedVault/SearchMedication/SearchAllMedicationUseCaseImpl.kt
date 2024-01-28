package com.example.medicalprescriptiontracker.Application.UseCases.HyperionMedVault.SearchMedication

import android.util.Log
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseCloudFirestoreRepository
import com.example.medicalprescriptiontracker.Medication

class SearchAllMedicationUseCaseImpl(
    private val medicationRepository: FirebaseCloudFirestoreRepository
) : SearchAllMedicationUseCase {

    override suspend fun searchAllMedication(searchQuery: String): List<Medication> {
        return try {
            medicationRepository.searchAllMedication(searchQuery)
        } catch (e: Exception) {
            Log.e("SearchAllMedicationUseCaseImpl", "Error searching medications", e)
            emptyList()
        }
    }
}