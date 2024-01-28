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
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicalprescriptiontracker.Application.ViewModelProvider.ViewModelProviderImpl
import com.example.medicalprescriptiontracker.Presentation.Screens.ProfileScreen
import com.example.medicalprescriptiontracker.ui.theme.MedicalPrescriptionTrackerTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicalPrescriptionTrackerTheme {

                // Firebase Authentication Initilisation
                val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
                // Jetpack NavController
                val navController = rememberNavController()

                // ViewModelProvider Implementations
                //val signInViewModel = ViewModelProviderImpl().provideSignInViewModel()
                //val homeViewModel = ViewModelProviderImpl().provideHomeViewModel()
                val viewModel = ViewModelProviderImpl().provideMedicalViewModel()
                val profileViewModel = ViewModelProviderImpl().provideProfileViewModel()

                // Navigation Drawer Items
                val items = listOf(
                    NavigationItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        route = Screen.Page1.route
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

                // Remember the state of the drawer
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                // Remember a coroutine scope to launch coroutines
                val scope = rememberCoroutineScope()
                // Remember the selected index of the item in Drawer
                var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

                // Modal Navigation Drawer
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
                    // Navigation Defined For App
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Page1.route,
                    ) {
                        composable(Screen.Home.route) {
                            //HomeScreen(signInViewModel, navController)
                        }
                        composable(Screen.Page1.route) {
                            /*val userId = firebaseAuth.currentUser?.uid
                            if (userId != null) {
                                Page1Screen(
                                    userId = userId,
                                    viewModel = homeViewModel,
                                    navController = navController
                                )
                            }*/
                        }
                        composable(Screen.Medication.route) {
                            MedicationListScreen(viewModel)
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

    data class NavigationItem(
        val title: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector,
        val route: String
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DrawerContent(
        items: List<NavigationItem>,
        selectedItemIndex: Int,
        onItemSelected: (Int) -> Unit,
        navController: NavController
    ) {
        ModalDrawerSheet {
            items.forEachIndexed { index, navigationItem ->
                NavigationDrawerItem(
                    label = { Text(text = navigationItem.title) },
                    selected = index == selectedItemIndex,
                    onClick = {
                        onItemSelected(index)
                        navController.navigate(navigationItem.route)
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                navigationItem.selectedIcon
                            } else navigationItem.unselectedIcon,
                            contentDescription = navigationItem.title
                        )
                    }
                )
            }
        }
    }
}