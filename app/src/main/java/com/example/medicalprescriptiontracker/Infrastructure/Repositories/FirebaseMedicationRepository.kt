package com.example.medicalprescriptiontracker.Infrastructure.Repositories

import android.util.Log
import com.example.medicalprescriptiontracker.Infrastructure.Service.MedicationRepository
import com.example.medicalprescriptiontracker.Domain.Entities.Medication
import com.example.medicalprescriptiontracker.Domain.Entities.Prescription
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

/**
 * Implementation of the [MedicationRepository] interface, providing data operations.
 */
class FirebaseMedicationRepository : MedicationRepository {

    private val firestore = Firebase.firestore

    /**
     * Retrieves all medications from the repository.
     *
     * @return A list of [Medication] objects.
     */
    override suspend fun getAllMedication(): List<Medication> =
        try {
            firestore.collection("medications")
                .get()
                .await()
                .documents.mapNotNull { it.toObject(Medication::class.java) }
        } catch (e: Exception) {
            handleException("getAllMedication", e)
            emptyList()
        }

    /**
     * Searches for medications in the repository based on the provided search query.
     *
     * @param searchQuery The search query to filter medications.
     * @return A list of [Medication] objects matching the search query.
     */
    override suspend fun searchAllMedication(searchQuery: String): List<Medication> =
        try {
            firestore.collection("medications")
                .get()
                .await()
                .documents.mapNotNull { it.toObject(Medication::class.java) }
                .filter { it.medicationName.contains(searchQuery, ignoreCase = true) }
        } catch (e: Exception) {
            handleException("searchAllMedication", e)
            emptyList()
        }

    /**
     * Adds a medication to the user's prescriptions in the repository with a "pending" status.
     * Admin approval is set to false by default.
     *
     * @param userId The unique identifier of the user.
     * @param medication The medication to be added.
     */
    override suspend fun addMedicationToUserPrescriptions(userId: String, medication: Medication) {
        try {
            val prescriptionId = UUID.randomUUID().toString()

            // Create a prescription with "pending" status
            val prescription = Prescription(
                prescriptionId = prescriptionId,
                medicationId = medication.id,
                medicationName = medication.medicationName,
                instructions = medication.specialInstructions,
                dosage = medication.dosage,
                createdAt = "",
                status = "pending" // Set status to "pending"
            )

            // Add the prescription to the user's collection
            firestore.collection("users")
                .document(userId)
                .collection("prescriptions")
                .add(prescription)
                .await()
        } catch (e: Exception) {
            handleException("addMedicationToUserPrescriptions", e)
        }
    }

    /**
     * Handles exceptions by logging an error message.
     *
     * @param methodName The name of the method where the exception occurred.
     * @param exception The caught exception.
     */
    private fun handleException(methodName: String, exception: Exception) {
        Log.e("FirebaseMedicationRepository", "Exception in $methodName: $exception")
    }
}