package com.example.medicalprescriptiontracker

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medicalprescriptiontracker.HyperionDashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HyperionDashboardScreen(
    userId: String,
    viewModel: HyperionDashboardViewModel,
    navController: NavController
) {
    val prescriptions by viewModel.prescriptions.collectAsState()

    LaunchedEffect(userId) {
        viewModel.loadPrescriptions(userId)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Dashboard",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 16.dp),
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ) {
                items(prescriptions) { prescription ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .height(100.dp)
                            .width(200.dp)
                            .border(1.dp, Color.Black, shape = MaterialTheme.shapes.small),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFF9800)
                        ),
                        onClick = {
                            // Navigate to the prescription detail screen and pass the prescription ID
                            //navController.navigate(Screen.PrescriptionDetail.route + "/${prescription.prescriptionId}")
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.Top // Adjusted verticalArrangement to Top
                        ) {
                            // Adjusted Text composable for better wrapping
                            Text(
                                text = "${prescription.medicationName}",
                                color = Color.Yellow, // Text color
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace, // Custom font
                            )
                            Text(
                                text = "${prescription.instructions}",
                                color = Color.White, // Text color
                                fontWeight = FontWeight.Bold,
                                fontSize = 8.sp,
                            )
                            Text(
                                text = "Dosage: ${prescription.dosage}",
                                color = Color.Yellow, // Hyperion yellow
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                            )
                            Text(
                                text = "Prescribed: ${prescription.createdAt}",
                                color = Color.White, // Text color
                                fontWeight = FontWeight.Bold,
                                fontSize = 8.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}