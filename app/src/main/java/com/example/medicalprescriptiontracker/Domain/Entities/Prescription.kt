package com.example.medicalprescriptiontracker.Domain.Entities

data class Prescription(
    val prescriptionId: String = "",
    val medicationId: String = "",  // Added field for Medication ID
    val medicationName: String = "",
    val instructions: String = "",
    val dosage: String = "",
    val createdAt: String = "",
    val status: String = ""
)