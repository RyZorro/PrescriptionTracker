package com.example.medicalprescriptiontracker

import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseCloudFirestoreRepository

/**
 * This implementation of the GetPrescriptionsUseCase interface handles the fetching logic.
 * @param repository The repository responsible for fetching Cloud Firestore operations.
 */
class GetAllPrescriptionUseCaseImpl(
    private val repository: FirebaseCloudFirestoreRepository
) : GetAllPrescriptionUseCase {
    override suspend fun getPrescriptions(userId: String): List<Prescription> {
        return repository.getPrescriptions(userId)
    }
}