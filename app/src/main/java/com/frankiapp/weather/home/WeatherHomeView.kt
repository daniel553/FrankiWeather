package com.frankiapp.weather.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.frankiapp.weather.model.WeatherCity

@Composable
fun WeatherHomeView(
    modifier: Modifier = Modifier,
    state: WeatherHomeState,
    onEvent: (WeatherHomeEvent) -> Unit
) {
    when (state) {
        WeatherHomeState.Loading -> WeatherHomeLoadingView(modifier)
        is WeatherHomeState.Success -> WeatherHomeListView(list = state.cities, modifier = modifier)
    }
}

@Preview
@Composable
private fun PreviewWeatherHomeView() {
    val state = WeatherHomeState.Success(cities = emptyList())
    WeatherHomeView(state = state, onEvent = {})
}

// Loading -------
@Composable
fun WeatherHomeLoadingView(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(modifier = Modifier.size(160.dp))
    }
}

@Preview
@Composable
private fun PreviewWeatherHomeLoadingView() {
    WeatherHomeLoadingView()
}

// List view --------

@Composable
fun WeatherHomeListView(
    modifier: Modifier = Modifier,
    list: List<WeatherCity> = emptyList()
) {
    if (list.isEmpty()) {
        Column(modifier) {

        }
    } else {
        LazyColumn(modifier) {
            items(list) {
                WeatherCityCard(
                    it, modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewWeatherHomeListView() {
    WeatherHomeListView()
}

// List item card ------
@Composable
fun WeatherCityCard(
    weatherCity: WeatherCity,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = { /*TODO*/ },
        colors = CardDefaults.elevatedCardColors().copy(),
        modifier = modifier
    ) {
        Text(text = weatherCity.name, modifier = Modifier.padding(8.dp))
    }
}

@Preview
@Composable
private fun PreviewWeatherCityCard() {
    WeatherCityCard(
        WeatherCity(
            name = "Los Angeles"
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

