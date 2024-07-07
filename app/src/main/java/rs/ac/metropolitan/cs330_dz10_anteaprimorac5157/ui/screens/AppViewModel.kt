package rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.Shipment
import rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.ShipmentModel
import rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.ui.navigation.NavigationRoutes

import java.net.URL

class AppViewModel : ViewModel() {
    lateinit var navController: NavHostController
    private lateinit var shipmentList: MutableList<Shipment>
    var granted = mutableStateOf(false)

    init {
        val jsonFile = loadResource("assets/posiljke.json").readText()
        val model = ShipmentModel.fromJson(jsonFile)
        model?.let {
            shipmentList = it.posiljke.toMutableStateList()
        }
    }

    private fun loadResource(path: String): URL {
        return Thread.currentThread().contextClassLoader.getResource(path)
    }

    val shipments: List<Shipment>
        get() = shipmentList.sortedBy { it.id }

    fun getShipment(id: String): Shipment? {
        return shipmentList.find { it.id == id }
    }

    fun navigateToShipmentDetails(id: String) {
        navController.navigate(NavigationRoutes.ShipmentDetailScreen.createRoute(id))
    }

    fun goBack() {
        navController.popBackStack()
    }
}