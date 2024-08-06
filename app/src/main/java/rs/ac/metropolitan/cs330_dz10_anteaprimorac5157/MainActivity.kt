package rs.ac.metropolitan.cs330_dz10_anteaprimorac5157

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.ui.navigation.NavSetup
import rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.ui.theme.CS330DZ10AnteaPrimorac5157

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CS330DZ10AnteaPrimorac5157 {
                navController = rememberNavController()
                Scaffold(topBar = {
                    TopAppBar(title = { Text(text = "Shipments") })
                }) { innerPadding ->
                    NavSetup(navController, innerPadding)
                }
            }
        }
    }
}