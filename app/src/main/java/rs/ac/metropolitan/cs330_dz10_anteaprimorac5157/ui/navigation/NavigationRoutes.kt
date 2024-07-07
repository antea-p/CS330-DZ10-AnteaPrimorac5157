package rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.ui.navigation

sealed class NavigationRoutes(val route: String) {
    object Home : NavigationRoutes(route = "home")
    object ShipmentDetailScreen: NavigationRoutes(route = "detail/{elementId}"){
        fun createRoute(elementId: String) = "detail/$elementId"
    }
}
