package com.rudimentum.meteo.screens.week

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rudimentum.meteo.R
import com.rudimentum.meteo.models.Forecastday
import com.rudimentum.meteo.models.Weather
import com.squareup.picasso.Picasso
import retrofit2.Response

class WeekForecastAdapter(): RecyclerView.Adapter<WeekForecastAdapter.MainHolder>() {

    private val picasso = Picasso.get()
    private var daysList = emptyList<Forecastday>()

    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayDate: TextView = view.findViewById(R.id.dayDate)
        val dayMinTemperature: TextView = view.findViewById(R.id.dayMinTemperature)
        val dayMaxTemperature: TextView = view.findViewById(R.id.dayMaxTemperature)
        val dayWeatherIcon: ImageView = view.findViewById(R.id.dayWeatherIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.day_item, parent, false)

        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val forecast = daysList[position]
        holder.dayMinTemperature.text = forecast.day.mintemp_c.toString()
        holder.dayMaxTemperature.text = forecast.day.maxtemp_c.toString()
        holder.dayDate.text = forecast.date
        picasso.load("https:${forecast.day.condition.icon}").into(holder.dayWeatherIcon)
    }

    override fun getItemCount(): Int = daysList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Forecastday>) {
        daysList = list
        notifyDataSetChanged()
    }
}
