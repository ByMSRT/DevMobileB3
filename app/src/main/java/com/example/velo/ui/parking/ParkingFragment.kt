package com.example.velo.ui.parking

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.velo.R
import com.example.velo.adapter.ParkingAdapter
import com.example.velo.adapter.StationAdapter
import com.example.velo.api.ParkingApi
import com.example.velo.api.RetrofitHelper
import com.example.velo.databinding.FragmentParkingBinding
import com.example.velo.model.allParking
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ParkingFragment : Fragment() {

    private var _binding: FragmentParkingBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val parkingViewModel = ViewModelProvider(this).get(ParkingViewModel::class.java)

        _binding = FragmentParkingBinding.inflate(inflater, container, false)
        val root : View = binding.root
        val progressBarParking = binding.progressBarParking
        val recyclerView = binding.recyclerViewParking
        parkingViewModel.parkings.observe(viewLifecycleOwner){
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = ParkingAdapter(it, requireContext())
            progressBarParking.visibility = View.GONE
            allParking = it
        }

        val parkingApi = RetrofitHelper().getInstance().create(ParkingApi::class.java)
        GlobalScope.launch {
            val result = parkingApi.getParkings()
            parkingViewModel.parkings.postValue(result.body())
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}