package com.example.velo.model

import android.location.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

var parkingSelected:Parking? = null
var currentLoc: Location? = null
var allParking: List<Parking>? = null

@Serializable
data class Parking (
    val id: Long,
    val name: String,
    val recordId: String,
    val grpNom: String,
    val grpStatut: Long,
    val grpExploitation: Long,
    val grpDisponible: Long,
    val longitude: Double,
    val latitude: Double,
    ) {
    fun toLocation(): Location {
        val location = Location("")

        location.latitude = latitude
        location.longitude = longitude

        return location
    }

    fun showDetails(): CharSequence? {
        return "$grpDisponible ✅ / $grpExploitation 🅿️"
    }
}


