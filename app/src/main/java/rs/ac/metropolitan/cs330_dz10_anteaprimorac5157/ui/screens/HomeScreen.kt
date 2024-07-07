package rs.ac.metropolitan.cs330l10pv.ui.screens

import android.Manifest
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.Shipment
import rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.ui.screens.AppViewModel

@Composable
fun HomeScreen(vm: AppViewModel, paddingValues: PaddingValues) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        vm.granted.value = isGranted
    }

    Column {
        if (!vm.granted.value) {
            InternetPermission(launcher)
        } else {
            Log.d("Antea HomeScreen", "Granted internet permission")
            ListShipments(vm = vm, paddingValues = paddingValues)
        }
    }
}

@Composable
private fun InternetPermission(launcher: ManagedActivityResultLauncher<String, Boolean>) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Internet permission not granted",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(8.dp)
            )
            Button(onClick = { launcher.launch(Manifest.permission.INTERNET) }) {
                Text("Request permission")
            }
        }
    }
}

@Composable
fun ListShipments(vm: AppViewModel, paddingValues: PaddingValues) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(vm.shipments) { shipment ->
            ShipmentItem(shipment) {
                vm.navigateToShipmentDetails(it)
            }
        }
    }
}

@Composable
fun ShipmentItem(shipment: Shipment, onSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { expanded = !expanded }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            BasicShipmentInfoRow(shipment, onSelected)
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                ShipmentAddressInfo(shipment)
                WeightInfo(shipment)
            }
        }
    }
}

@Composable
private fun BasicShipmentInfoRow(
    shipment: Shipment,
    onSelected: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = shipment.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = shipment.email,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Light),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        IconButton(
            onClick = { onSelected(shipment.id) },
        ) {
            Icon(Icons.Default.ArrowForward, contentDescription = "View details")
        }
    }
}

@Composable
private fun ShipmentAddressInfo(shipment: Shipment) {
    Row {
        Text(
            text = "To: ",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "${shipment.countryTo}, ${shipment.cityTo}, ${shipment.streetTo}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun WeightInfo(shipment: Shipment) {
    Row {
        Text(
            text = "Weight: ",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "${shipment.weight} kg",
            style = MaterialTheme.typography.bodyMedium
        )
    }
    if (shipment.lomljivo) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Rounded.Warning,
                contentDescription = "Fragile",
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("Fragile", color = MaterialTheme.colorScheme.error)
        }
    }
}


//@Preview
//@Composable
//fun InternetPermissionPreview() {
//    InternetPermission(rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { })
//}

//@Preview
//@Composable
//fun HomeScreenPreview() {
//    val vm = AppViewModel()
//    HomeScreen(vm, PaddingValues(0.dp))
//}
