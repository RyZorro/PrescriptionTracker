package com.example.medicalprescriptiontracker.Presentation.Screen

sealed class Screen(val route: String) {
    object SignIn: Screen("sign_in")
    object Home: Screen("home")
    object Medication: Screen("medication")
    object Profile: Screen("profile")
    object HyperionDashboard: Screen("hyperion_dashboard")
}