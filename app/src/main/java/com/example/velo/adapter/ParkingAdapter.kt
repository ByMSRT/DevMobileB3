package com.example.velo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.velo.R
import com.example.velo.model.*
import com.example.velo.ui.parking.ParkingMapsActivity
import com.example.velo.ui.station.StationMapsActivity

class ParkingAdapter(private val parkings:List<Parking>, private val context: Context) : RecyclerView.Adapter<ParkingAdapter.ViewHolder>() {

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val cardView : CardView = itemView.findViewById(R.id.cardViewParking)
        val name : TextView = itemView.findViewById(R.id.nameParking)
        val availability : TextView = itemView.findViewById(R.id.nameParking2)
        val distance : TextView = itemView.findViewById(R.id.distanceParking)
        val parkStatus : ImageView = itemView.findViewById(R.id.parkAbo)
        val parkingImg : ImageView = itemView.findViewById(R.id.imageViewParking)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardviewparking_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parking = parkings[position]
        holder.name.text = parking.grpNom
        holder.availability.text = parking.showDetails()

        if (currentLocation != null) {
            holder.distance.text = "${String.format("%.2f",currentLocation!!.distanceTo(parking!!.toLocation())/1000)} km"
        } else {
            holder.distance.text = "No data"
        }

        val numIndisponible = 0
        val numClose = 1
        val numSubs = 2
        val numOpen = 5

        if (numIndisponible == parking.grpStatut.toInt()) {
           holder.parkStatus.setImageResource(R.drawable.ic_baseline_money_off_24)
        } else if (numClose == parking.grpStatut.toInt()) {
            holder.parkStatus.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
        } else if (numSubs == parking.grpStatut.toInt()) {
            holder.parkStatus.setImageResource(R.drawable.ic_baseline_report_problem_24)
        } else {
            holder.parkStatus.setImageResource(R.drawable.ic_baseline_radio_button_checked_24)
        }

        holder.cardView.setOnClickListener{
            val intent = Intent(context, ParkingMapsActivity::class.java)
            parkingSelected = parking
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return parkings.size
    }
}