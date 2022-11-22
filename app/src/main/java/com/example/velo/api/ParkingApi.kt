package com.example.velo.api

import com.example.velo.model.Parking
import com.example.velo.model.Station
import retrofit2.Response
import retrofit2.http.GET

interface ParkingApi {
    @GET("api/parking")
    suspend fun getParkings(): Response<List<Parking>>
}