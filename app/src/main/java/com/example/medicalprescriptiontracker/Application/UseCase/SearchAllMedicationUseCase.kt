package com.example.medicalprescriptiontracker.Application.UseCase

import com.example.medicalprescriptiontracker.Medication

/**
 * Use case interface for searching medications based on a query.
 */
interface SearchAllMedicationUseCase {
    suspend fun searchAllMedication(searchQuery: String): List<Medication>
}