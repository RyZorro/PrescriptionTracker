package com.example.medicalprescriptiontracker

data class Prescription(
    val prescriptionId: String = "",
    val medicationName: String = "",
    val instructions: String = "",
    val dosage: String = "",
    val createdAt: String = ""
)
