package com.example.medicalprescriptiontracker.Domain.Entities

data class Medication(
    val id: String = "",
    val medicationName: String = "",
    val dosage: String = "",
    val frequency: String = "",
    val sideEffects: String = "",
    val specialInstructions: String = "",
    val status: String = ""
)