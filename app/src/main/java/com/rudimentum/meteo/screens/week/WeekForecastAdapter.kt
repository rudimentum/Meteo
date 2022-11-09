package com.rudimentum.meteo.screens.week

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rudimentum.meteo.R
import com.rudimentum.meteo.models.DayItem
import com.squareup.picasso.Picasso

class WeekForecastAdapter(): RecyclerView.Adapter<WeekForecastAdapter.MainHolder>() {

    private val picasso = Picasso.get()
    private var daysList = emptyList<DayItem>()

    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayMinTemperature: TextView = view.findViewById(R.id.dayMinTemperature)
        val dayMaxTemperature: TextView = view.findViewById(R.id.dayMaxTemperature)
        val dayWeatherIcon: ImageView = view.findViewById(R.id.dayWeatherIcon)
    }

    override fun onViewDetachedFromWindow(holder: MainHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.day_item, parent, false)

        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val day = daysList[position]

        holder.dayMinTemperature.text = day.minTemperature
        holder.dayMaxTemperature.text = day.maxTemperature
        picasso.load(day.image).into(holder.dayWeatherIcon)
    }

    override fun getItemCount(): Int = daysList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<DayItem>) {
        daysList = list
        notifyDataSetChanged()
    }
}