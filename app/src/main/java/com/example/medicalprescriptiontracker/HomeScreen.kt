package com.example.medicalprescriptiontracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medicalprescriptiontracker.Screen
import com.example.medicalprescriptiontracker.SignInForm
import com.example.medicalprescriptiontracker.SignInViewModel

/**
 * Composable function representing the sign-in screen.
 * @param signInViewModel ViewModel responsible for handling sign-in operations.
 * @param navController NavController for navigation within the app.
 */
@Composable
fun HomeScreen(signInViewModel: SignInViewModel, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val signInResult by signInViewModel.signInResult.collectAsState()

    if (signInResult != null) {
        navController.navigate(Screen.Page1.route)
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Medication App",
                    color = Color(0xFF000000),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
                SignInForm(email, password, onSignInClick = { userEmail, userPassword ->
                    signInViewModel.signIn(userEmail, userPassword)
                })
            }
        }
    }
}