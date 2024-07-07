package rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_dz10_anteaprimorac5157.Shipment

@Composable
fun ShipmentDetailScreen(vm: AppViewModel, shipmentId: String, paddingValues: PaddingValues) {
    val shipment = vm.getShipment(shipmentId)
    var showSenderInfo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        shipment?.let {
            Text(
                text = it.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = it.email,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (showSenderInfo) {
                SenderInfo(shipment)
            } else {
                ReceiverInfo(shipment)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Weight: ${it.weight} kg", style = MaterialTheme.typography.bodyMedium)
                if (it.lomljivo) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = "Fragile",
                        tint = MaterialTheme.colorScheme.error
                    )
                    Text("Fragile", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.error)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { showSenderInfo = !showSenderInfo },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(if (showSenderInfo) "To" else "From")
            }
        } ?: Text("Shipment not found")
    }
}

@Composable
fun SenderInfo(shipment: Shipment) {
    Column {
        Text("From:", style = MaterialTheme.typography.titleMedium)
        Text("Country: ${shipment.countryFrom}")
        Text("City: ${shipment.cityFrom}")
        Text("Street: ${shipment.streetFrom}")
    }
}

@Composable
fun ReceiverInfo(shipment: Shipment) {
    Column {
        Text("To:", style = MaterialTheme.typography.titleMedium)
        Text("Country: ${shipment.countryTo}")
        Text("City: ${shipment.cityTo}")
        Text("Street: ${shipment.streetTo}")
    }
}