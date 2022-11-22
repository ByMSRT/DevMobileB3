package com.example.velo.ui.parking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.velo.model.Parking

class ParkingViewModel : ViewModel() {
    private val _parking = MutableLiveData<List<Parking>>().apply {
        value = ArrayList()
    }
    val parkings: MutableLiveData<List<Parking>> = _parking
}