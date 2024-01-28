package com.example.medicalprescriptiontracker

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalprescriptiontracker.Prescription

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionItemComposable(
    prescription: Prescription,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(100.dp)
            .width(200.dp)
            .border(1.dp, Color.Black, shape = MaterialTheme.shapes.small),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFF9800)
        ),
        onClick = onClick
    ) {
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
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = "${prescription.instructions}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 8.sp,
            )
            Text(
                text = "Dosage: ${prescription.dosage}",
                color = Color.Yellow,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
            )
            Text(
                text = "Prescribed: ${prescription.createdAt}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 8.sp,
            )
        }
    }
}