package com.example.medicalprescriptiontracker.Presentation.Components.HyperionDashboard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medicalprescriptiontracker.Domain.Entities.Prescription

@Composable
fun PrescriptionCardsRow(
    prescriptions: List<Prescription>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp) // Adjust the height as needed
    ) {
        items(prescriptions) { prescription ->
            PrescriptionCard(prescription)
        }
    }
}