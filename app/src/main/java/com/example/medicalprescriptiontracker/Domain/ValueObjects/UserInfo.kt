package com.example.medicalprescriptiontracker.Domain.ValueObjects

data class UserInfo(
    val allergies: String = "",
    val chronic_conditions: String = "",
    val emergencyContact: String = ""
)