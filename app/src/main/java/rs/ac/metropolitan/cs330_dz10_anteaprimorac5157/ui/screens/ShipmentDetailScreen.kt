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
import androidx.compose.material.icons.rounded.Warning
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
            NameAndEmail(it)

            if (showSenderInfo) {
                FromInfo(shipment)
            } else {
                ToInfo(shipment)
            }

            Spacer(modifier = Modifier.height(16.dp))

            WeightInfo(it)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { showSenderInfo = !showSenderInfo },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(if (showSenderInfo) "To" else "From")
            }
        } ?: Text("Shipment not found", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun NameAndEmail(it: Shipment) {
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
}

@Composable
fun FromInfo(shipment: Shipment) {
    Column {
        Text("From:", style = MaterialTheme.typography.titleMedium)
        Row {
            Text("Country: ", style = MaterialTheme.typography.labelLarge)
            Text(shipment.countryFrom, style = MaterialTheme.typography.bodyMedium)
        }
        Row {
            Text("City: ", style = MaterialTheme.typography.labelLarge)
            Text(shipment.cityFrom, style = MaterialTheme.typography.bodyMedium)
        }
        Row {
            Text("Street: ", style = MaterialTheme.typography.labelLarge)
            Text(shipment.cityTo, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ToInfo(shipment: Shipment) {
    Column {
        Text("To:", style = MaterialTheme.typography.titleMedium)
        Row {
            Text("Country: ", style = MaterialTheme.typography.labelLarge)
            Text(shipment.countryTo, style = MaterialTheme.typography.bodyMedium)
        }
        Row {
            Text("City: ", style = MaterialTheme.typography.labelLarge)
            Text(shipment.cityTo, style = MaterialTheme.typography.bodyMedium)
        }
        Row {
            Text("Street: ", style = MaterialTheme.typography.labelLarge)
            Text(shipment.streetTo, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun WeightInfo(it: Shipment) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Weight: ", style = MaterialTheme.typography.labelLarge)
        Text("${it.weight} kg", style = MaterialTheme.typography.bodyMedium)
        if (it.lomljivo) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                Icons.Rounded.Warning,
                contentDescription = "Fragile",
                tint = MaterialTheme.colorScheme.error
            )
            Text(
                "Fragile",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}