package rs.ac.metropolitan.cs330_dz10_anteaprimorac5157

import android.content.Context
import com.beust.klaxon.Klaxon
import java.io.BufferedReader

private val klaxon = Klaxon()

data class ShipmentModel(val posiljke: List<Shipment>) {
    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<ShipmentModel>(json)

        fun readAsset(context: Context, fileName: String): String =
            context
                .assets
                .open(fileName)
                .bufferedReader()
                .use(BufferedReader::readText)
    }
}

data class Shipment(
    val id: String,
    val countryFrom: String,
    val cityFrom: String,
    val streetFrom: String,
    val countryTo: String,
    val cityTo: String,
    val streetTo: String,
    val name: String,
    val email: String,
    val weight: Int,
    val lomljivo: Boolean
)