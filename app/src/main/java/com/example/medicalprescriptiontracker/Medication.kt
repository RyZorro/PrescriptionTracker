package com.example.medicalprescriptiontracker

data class Medication(
    val medicationName: String = "",
    val dosage: String = "",
    val frequency: String = "",
    val sideEffects: String = "",
    val specialInstructions: String = "",
)