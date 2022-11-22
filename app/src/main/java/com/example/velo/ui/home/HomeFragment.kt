package com.example.velo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.velo.adapter.StationAdapter
import com.example.velo.api.RetrofitHelper
import com.example.velo.api.StationApi
import com.example.velo.databinding.FragmentHomeBinding
import com.example.velo.model.allStations
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var progressBarStation = binding.progressBarStations
        val recyclerView = binding.recyclerView
        homeViewModel.stations.observe(viewLifecycleOwner){
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = StationAdapter(it, requireContext())
            progressBarStation.visibility = View.GONE
            allStations = it
        }

        val stationApi = RetrofitHelper().getInstance().create(StationApi::class.java)
        GlobalScope.launch {
            val result = stationApi.getStations()
            homeViewModel.stations.postValue(result.body())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}