package com.example.medicalprescriptiontracker.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.medicalprescriptiontracker.Presentation.Components.ProfileScreen.MedicalInfoBody
import com.example.medicalprescriptiontracker.Presentation.Components.ProfileScreen.UserProfileBody
import com.example.medicalprescriptiontracker.Presentation.Components.ProfileScreen.UserProfileHeader
import com.example.medicalprescriptiontracker.Presentation.ViewModels.HyperionProfileViewModel

@Composable
fun ProfileScreen(userId: String, viewModel: HyperionProfileViewModel) {
    val userProfile by viewModel.userUserProfile.collectAsState()
    val medicalInfo by viewModel.userInfo.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUserProfile(userId)
        viewModel.fetchUserMedicalInfo(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        UserProfileHeader(userProfile)

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.Black)
                .padding(vertical = 8.dp)
        )

        Text(text = "Profile")
        userProfile?.let { UserProfileBody(it) }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.Black)
                .padding(vertical = 8.dp)
        )

        Text(text = "Medical Information")
        medicalInfo?.let { MedicalInfoBody(it) }
    }
}