package com.example.medicalprescriptiontracker.Domain.ValueObjects

data class UserProfile(
    val bloodType: String = "",
    val contactInformation: String = "",
    val dateOfBirth: String = "",
    val fullName: String = "",
    val gender: String = "",
    val image: String = ""
)