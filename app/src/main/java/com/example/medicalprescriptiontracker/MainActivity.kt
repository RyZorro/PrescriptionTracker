package com.example.medicalprescriptiontracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicalprescriptiontracker.Presentation.Screens.ProfileScreen
import com.example.medicalprescriptiontracker.Presentation.Screen.Screen
import com.example.medicalprescriptiontracker.Application.ViewModelProvider.ViewModelProviderImpl
import com.example.medicalprescriptiontracker.Domain.ValueObjects.NavigationItem
import com.example.medicalprescriptiontracker.Presentation.NavigationDrawer.DrawerContent
import com.example.medicalprescriptiontracker.Presentation.Screens.HomeScreen
import com.example.medicalprescriptiontracker.Presentation.Screens.HyperionDashboardScreen
import com.example.medicalprescriptiontracker.Presentation.Screens.HyperionMedVaultScreen
import com.example.medicalprescriptiontracker.Presentation.Screens.SignInScreen
import com.example.medicalprescriptiontracker.ui.theme.MedicalPrescriptionTrackerTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicalPrescriptionTrackerTheme {

                val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
                val navController = rememberNavController()

                val medVaultViewModel = ViewModelProviderImpl().provideHyperionMedVaultViewModel()
                val profileViewModel = ViewModelProviderImpl().provideHyperionProfileViewModel()
                val hyperionDashboard = ViewModelProviderImpl().provideHyperionDashboardViewModel()
                val signInViewModel = ViewModelProviderImpl().provideSignInViewModel()

                val items = listOf(
                    NavigationItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        route = Screen.Home.route
                    ),
                    NavigationItem(
                        title = "Dashboard",
                        selectedIcon = Icons.Filled.Info,
                        unselectedIcon = Icons.Outlined.Info,
                        route = Screen.HyperionDashboard.route
                    ),
                    NavigationItem(
                        title = "Hyperion Med Vault",
                        selectedIcon = Icons.Filled.Info,
                        unselectedIcon = Icons.Outlined.Info,
                        route = Screen.Medication.route
                    ),
                    NavigationItem(
                        title = "Hyperion Profile",
                        selectedIcon = Icons.Filled.AccountCircle,
                        unselectedIcon = Icons.Outlined.AccountCircle,
                        route = Screen.Profile.route
                    )
                )

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

                ModalNavigationDrawer(
                    drawerContent = {
                        DrawerContent(
                            items = items,
                            selectedItemIndex = selectedItemIndex, onItemSelected = { index ->
                                selectedItemIndex = index
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            navController = navController
                        )
                    },
                    drawerState = drawerState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SignIn.route,
                    ) {
                        composable(Screen.SignIn.route) {
                            SignInScreen(signInViewModel, navController)
                        }
                        composable(Screen.Home.route) {
                            HomeScreen()
                        }
                        composable(Screen.Medication.route) {
                            HyperionMedVaultScreen(medVaultViewModel)
                        }
                        composable(Screen.HyperionDashboard.route) {
                            val userId = firebaseAuth.currentUser?.uid
                            if (userId != null) {
                                HyperionDashboardScreen(
                                    userId = userId,
                                    viewModel = hyperionDashboard ,
                                    navController = navController
                                )
                            }
                        }
                        composable(Screen.Profile.route) {
                            val userId = firebaseAuth.currentUser?.uid
                            if (userId != null) {
                                ProfileScreen(userId, profileViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}