package com.example.medicalprescriptiontracker.Infrastructure.Service

import com.example.medicalprescriptiontracker.Domain.Entities.Prescription

/**
 * Repository interface for user prescriptions-related operations.
 */
interface UserPrescriptionRepository {

    /**
     * Retrieves all prescriptions for the specified user ID.
     *
     * @param userId The unique identifier of the user.
     * @return A list of [Prescription] objects representing the user's prescriptions.
     */
    suspend fun getUserPrescriptions(userId: String): List<Prescription>
}