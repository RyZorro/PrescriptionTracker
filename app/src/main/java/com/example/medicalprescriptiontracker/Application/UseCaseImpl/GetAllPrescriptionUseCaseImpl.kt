package com.example.medicalprescriptiontracker.Application.UseCaseImpl

import com.example.medicalprescriptiontracker.Application.UseCase.GetAllPrescriptionUseCase
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseCloudFirestoreRepository
import com.example.medicalprescriptiontracker.Prescription

/**
 * This implementation of the GetPrescriptionsUseCase interface handles the fetching logic.
 * @param repository The repository responsible for fetching Cloud Firestore operations.
 */
class GetAllPrescriptionUseCaseImpl(
    private val repository: FirebaseCloudFirestoreRepository
) : GetAllPrescriptionUseCase {

    /**
     * Retrieves a list of all prescriptions from the repository.
     *
     * @return List of Prescription objects representing all medications.
     */
    override suspend fun getPrescriptions(userId: String): List<Prescription> {
        return repository.getPrescriptions(userId)
    }
}