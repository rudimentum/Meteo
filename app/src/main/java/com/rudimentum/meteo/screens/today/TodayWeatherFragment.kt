package com.rudimentum.meteo.screens.today

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.rudimentum.meteo.databinding.FragmentTodayBinding
import com.rudimentum.meteo.models.Weather
import com.rudimentum.meteo.repository.WeatherRepository
import com.rudimentum.meteo.utils.*
import com.squareup.picasso.Picasso
import retrofit2.Response


class TodayWeatherFragment : Fragment() {

    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private lateinit var locationClient: FusedLocationProviderClient
    private var _binding: FragmentTodayBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TodayWeatherViewModel by lazy {
        ViewModelProvider(this, TodayWeatherViewModelFactory(repository = WeatherRepository()))
            .get(TodayWeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        checkPermission()
        locationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        binding.sync.setOnClickListener {
            getLocation()
            getData()
        }
        binding.search.setOnClickListener{
            DialogManager.showCityDialog(requireContext())
            getData()
        }
        getData()
    }

    override fun onResume() {
        super.onResume()
        checkLocation()
    }

    private fun getData() {
        viewModel.getTodayWeather()
        viewModel.liveDataCurrent.observe(viewLifecycleOwner) { response ->
            setDataToViews(response)
        }
    }

    private fun setDataToViews(response: Response<Weather>?) {
        val picasso = Picasso.get()
        val data = response?.body()!!
        binding.currentCity.text = data.location.name
        binding.currentTemperature.text = data.current.temp_c.toString()
        picasso.load("https:${data.current.condition.icon}").into(binding.dayWeatherIcon)
    }

    private fun permissionListener() {
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()) {
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun checkLocation() {
        if (isLocationEnabled()) {
            getLocation()
        } else {
            DialogManager.locationSettingDialog(requireContext(), object: DialogManager.Listener{
                override fun onClick() {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }
            })
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun getLocation() {
        val ct = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, ct.token)
            .addOnCompleteListener {
                CITY_NAME = ("${it.result.latitude},${it.result.longitude}")
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}