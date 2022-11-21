package com.rudimentum.meteo.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rudimentum.meteo.R
import com.rudimentum.meteo.databinding.ActivityMainBinding
import com.rudimentum.meteo.screens.today.TodayWeatherFragment
import com.rudimentum.meteo.screens.week.WeekForecastFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val todayFragment = TodayWeatherFragment()
        val weekFragment = WeekForecastFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayoutFragment, todayFragment)
            commit()
        }

        binding.bottomNavigation.setOnItemSelectedListener() { item ->
            when(item.itemId) {
                R.id.today -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayoutFragment, todayFragment)
                        commit()
                    }
                    true
                }
                R.id.week -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayoutFragment, weekFragment)
                        commit()
                    }
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}