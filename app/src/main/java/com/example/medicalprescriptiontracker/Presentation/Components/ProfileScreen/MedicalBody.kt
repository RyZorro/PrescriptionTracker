package com.example.medicalprescriptiontracker.Presentation.Components.ProfileScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserInfo

@Composable
fun MedicalInfoBody(userInfo: UserInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        DetailItem("Allergies", userInfo.allergies)
        DetailItem("Chronic Conditions", userInfo.chronic_conditions)
        DetailItem("Emergency Contact", userInfo.emergencyContact)
    }
}