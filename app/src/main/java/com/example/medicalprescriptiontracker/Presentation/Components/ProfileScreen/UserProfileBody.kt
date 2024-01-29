package com.example.medicalprescriptiontracker.Presentation.Components.ProfileScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserProfile

@Composable
fun UserProfileBody(userUserProfile: UserProfile) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        DetailItem("Gender", userUserProfile.gender)
        DetailItem("Date Of Birth", userUserProfile.dateOfBirth)
        DetailItem("Blood Type", userUserProfile.bloodType)
        DetailItem("Contact", userUserProfile.contactInformation)
    }
}