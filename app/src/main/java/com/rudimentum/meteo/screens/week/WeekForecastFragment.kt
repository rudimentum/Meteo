package com.rudimentum.meteo.screens.week

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rudimentum.meteo.databinding.FragmentWeekBinding
import com.rudimentum.meteo.repository.WeatherRepository

class WeekForecastFragment : Fragment() {

    private var _binding: FragmentWeekBinding? = null
    private val binding get() = _binding!!
    private lateinit var weekForecastAdapter: WeekForecastAdapter
    private val viewModel: WeekForecastViewModel by lazy {
            ViewModelProvider(this, WeekForecastViewModelFactory(repository = WeatherRepository()))
                .get(WeekForecastViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getWeekForecast()
        viewModel.liveDataCurrent.observe(viewLifecycleOwner) { list ->
                list.body()?.let { weekForecastAdapter.setList(it.forecast.forecastday) }
        }
    }

    private fun setupRecyclerView() = with(binding) {
        recyclerViewDaysForecast.layoutManager = LinearLayoutManager(activity)
        weekForecastAdapter = WeekForecastAdapter()
        recyclerViewDaysForecast.adapter = weekForecastAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}