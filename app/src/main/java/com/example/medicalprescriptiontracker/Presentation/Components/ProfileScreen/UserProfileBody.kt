package com.example.medicalprescriptiontracker.Presentation.Components.ProfileScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.medicalprescriptiontracker.UserInfo
import com.example.medicalprescriptiontracker.UserProfile

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

@Composable
fun DetailItem(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$title:",
            style = MaterialTheme.typography.bodySmall.copy(
                fontFamily = FontFamily.SansSerif,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(
                fontFamily = FontFamily.SansSerif
            )
        )
    }
}

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