package com.frankiapp.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.frankiapp.weather.home.WeatherHomeScreen
import com.frankiapp.weather.ui.theme.FrankiWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FrankiWeatherTheme {
                WeatherHomeScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
