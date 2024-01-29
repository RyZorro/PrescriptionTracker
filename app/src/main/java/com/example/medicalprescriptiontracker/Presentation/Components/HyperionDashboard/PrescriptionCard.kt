package com.example.medicalprescriptiontracker.Presentation.Components.HyperionDashboard

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.medicalprescriptiontracker.Domain.Entities.Prescription

@Composable
fun PrescriptionCard(
    prescription: Prescription
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(100.dp)
            .width(200.dp)
            .border(1.dp, Color.Black, shape = MaterialTheme.shapes.small),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFF9800)
        )
    ) {
        PrescriptionCardContent(prescription)
    }
}