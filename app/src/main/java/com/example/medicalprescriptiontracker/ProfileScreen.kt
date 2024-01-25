package com.example.medicalprescriptiontracker

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medicalprescriptiontracker.Presentation.Components.ProfileScreen.UserProfileHeader
import com.example.medicalprescriptiontracker.Presentation.ViewModels.ProfileViewModel

@Composable
fun ProfileScreen(userId: String, viewModel: ProfileViewModel) {
    val userProfile by viewModel.userProfile.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUserProfile(userId)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        UserProfileHeader(userProfile)
    }
}