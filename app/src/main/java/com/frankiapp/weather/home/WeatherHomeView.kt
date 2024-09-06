package com.frankiapp.weather.home

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.frankiapp.weather.R
import com.frankiapp.weather.model.WeatherCity
import com.frankiapp.weather.model.WeatherCityItem
import com.frankiapp.weather.model.WeatherCityMain
import com.frankiapp.weather.ui.theme.FrankiWeatherTheme
import com.frankiapp.weather.util.toFahrenheit

private val sampleCities = (1..3).map {
    WeatherCity(
        name = "Los Angeles $it",
        weather = listOf(WeatherCityItem(main = "Cloudy")),
        main = WeatherCityMain(305f, 300f, 310f)
    )
}

@Composable
fun WeatherHomeView(
    modifier: Modifier = Modifier,
    state: WeatherHomeState,
    onEvent: (WeatherHomeEvent) -> Unit
) {
    when (state) {
        WeatherHomeState.Loading -> WeatherHomeLoadingView(modifier)
        is WeatherHomeState.Success -> WeatherHomeListView(
            list = state.cities,
            modifier = Modifier,
            onEvent = { onEvent(it) }
        )
    }
}

@Preview
@Composable
private fun PreviewWeatherHomeView() {
    FrankiWeatherTheme {
        val state = WeatherHomeState.Success(cities = sampleCities)
        WeatherHomeView(state = state, onEvent = {})
    }
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

// List view --------

@Composable
fun WeatherHomeListView(
    modifier: Modifier = Modifier,
    onEvent: (WeatherHomeEvent) -> Unit,
    list: List<WeatherCity> = emptyList()
) {
    if (list.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "search",
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = stringResource(id = R.string.home_empty_title),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            AddCityButtonView(
                onClick = { onEvent(WeatherHomeEvent.OnAddNew) },
                modifier = Modifier
                    .widthIn(128.dp)
                    .height(32.dp)
            )
        }
    } else {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            items(list) {
                WeatherCityCard(
                    it, modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            item {
                AddCityButtonView(
                    onClick = { onEvent(WeatherHomeEvent.OnAddNew) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(widthDp = 420, heightDp = 300)
@Composable
private fun PreviewWeatherHomeListEmptyView() {
    FrankiWeatherTheme {
        WeatherHomeListView(list = emptyList(), onEvent = {})
    }
}

@Preview
@Composable
private fun PreviewWeatherHomeListView() {
    FrankiWeatherTheme {
        WeatherHomeListView(list = sampleCities, onEvent = {})
    }
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
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(0.6f)) {
                Text(
                    text = weatherCity.name,
                    style = MaterialTheme.typography.headlineLarge,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(8.dp)
                        .basicMarquee(iterations = 1, initialDelayMillis = 2_000)
                )
                weatherCity.weather.first()?.let {
                    Text(
                        text = "Main: ${it.main}", modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                Text(
                    text = "Min: ${weatherCity.main.tempMin.toFahrenheit()}° - Max: ${weatherCity.main.tempMax.toFahrenheit()}°",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(0.4f)
            ) {
                Icon(
                    imageVector = weatherCity.weatherIcon(),
                    contentDescription = "icon",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(48.dp)
                )
                Text(
                    text = "${weatherCity.main.temp.toFahrenheit()}°F",
                    maxLines = 1,
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W700),
                    modifier = Modifier.padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        start = 8.dp,
                        end = 16.dp
                    )
                )
            }
        }
    }
}

@Composable
private fun WeatherCity.weatherIcon(): ImageVector {
    return when {
        weather.isEmpty() -> Icons.Default.WbSunny
        weather.first().icon == "01d" -> Icons.Default.WbSunny
        weather.first().icon == "03n" -> Icons.Default.WbCloudy
        else -> Icons.Default.Thunderstorm
    }
}

@Preview
@Composable
private fun PreviewWeatherCityCard() {
    FrankiWeatherTheme {
        WeatherCityCard(
            sampleCities.first(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

//Add city view ---

@Composable
fun AddCityButtonView(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "add")
        Text(text = stringResource(id = R.string.home_add_city).uppercase())
    }
}

