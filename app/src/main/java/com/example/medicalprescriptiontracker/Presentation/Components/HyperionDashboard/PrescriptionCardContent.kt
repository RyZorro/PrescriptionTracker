package com.example.medicalprescriptiontracker.Presentation.Components.HyperionDashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalprescriptiontracker.Domain.Entities.Prescription

@Composable
fun PrescriptionCardContent(prescription: Prescription) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "${prescription.medicationName}",
            color = Color.Yellow,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            fontFamily = FontFamily.Monospace,
        )
        Text(
            text = "${prescription.instructions}",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
        )
        Text(
            text = "Dosage: ${prescription.dosage}",
            color = Color.Yellow,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
        )
        Text(
            text = "Prescribed: ${prescription.createdAt}",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
        )
    }
}