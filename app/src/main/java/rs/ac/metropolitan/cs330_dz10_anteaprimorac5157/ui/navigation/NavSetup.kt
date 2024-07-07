package rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.ui.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.ui.screens.AppViewModel
import rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.ui.screens.ShipmentDetailScreen
import rs.ac.metropolitan.cs330l10pv.ui.screens.HomeScreen

@Composable
fun NavSetup(navController: NavHostController, paddingValues: PaddingValues) {
    val vm: AppViewModel = viewModel()
    vm.navController = navController

    NavHost(navController = navController, startDestination = NavigationRoutes.Home.route) {
        composable(route = NavigationRoutes.Home.route) {
            HomeScreen(vm, paddingValues)
        }
        composable(route = NavigationRoutes.ShipmentDetailScreen.route) { navBackStackEntry ->
            val elementId = navBackStackEntry.arguments?.getString("elementId")
            if (elementId != null) {
                ShipmentDetailScreen(vm, elementId, paddingValues)
            } else {
                Toast.makeText(navController.context, "Error, elementId is required!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}