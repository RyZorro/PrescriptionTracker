package com.example.medicalprescriptiontracker.Application.UseCases.GetUserPrescriptions

import com.example.medicalprescriptiontracker.Domain.Entities.Prescription

/**
 * Use case interface for retrieving user prescriptions.
 */
interface GetUserPrescriptionUseCase {

    /**
     * Retrieves a list of prescriptions for the specified user ID.
     *
     * @param userId The unique identifier of the user.
     * @return A list of [Prescription] objects representing the user's prescriptions.
     */
    suspend fun getPrescriptions(userId: String): List<Prescription>
}