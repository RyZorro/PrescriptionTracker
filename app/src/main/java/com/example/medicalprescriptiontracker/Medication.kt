package com.example.medicalprescriptiontracker

data class Medication(
    val id: String = "",
    val medicationName: String = "",
    val manufacturer: String = "",
    val dosage: String = "",
    val frequency: String = "",
    val sideEffects: String = "",
    val specialInstructions: String = ""
)