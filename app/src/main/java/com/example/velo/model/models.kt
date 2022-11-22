package com.example.velo.model

import android.location.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

var stationSelected:Station? = null
var currentLocation: Location? = null
var allStations:List<Station>? = null


@Serializable
data class Station (
    val id: Long,
    val name: String,
    val lattitude: Double,
    val longitude: Double,
    val status: String,
    val bikeStands: Long,
    val availableBikes: Long,
    val availableBikeStands: Long,
    val recordId: String,
    val address: String
) {
    fun toLocation() : Location {
        val location = Location("")

        location.latitude = lattitude
        location.longitude = longitude

        return location
    }

    fun showDetails(): CharSequence? {
        return "$availableBikes 🚲 / $availableBikeStands 🚳 / $bikeStands 🅿️️"
    }
}

