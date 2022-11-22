package com.example.velo.api

import com.example.velo.model.Station
import retrofit2.Response
import retrofit2.http.GET

interface StationApi {
    @GET("api/stations")
    suspend fun getStations(): Response<List<Station>>
}