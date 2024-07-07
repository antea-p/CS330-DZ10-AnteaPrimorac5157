package rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun BackButton(navController: NavHostController){
    Button(onClick = { navController.popBackStack() }) {
        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
    }
}

@Preview
@Composable
fun PreviewBackButton() {
    val navController = rememberNavController()
    BackButton(navController)
}