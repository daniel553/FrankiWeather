package com.frankiapp.weather.home.addcity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.frankiapp.weather.R
import com.frankiapp.weather.model.WeatherCity
import com.frankiapp.weather.ui.theme.FrankiWeatherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCityView(
    state: AddCityState,
    onEvent: (AddCityEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = state.search,
                    onQueryChange = { onEvent(AddCityEvent.OnSearchChanged(it)) },
                    onSearch = { expanded = false },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text(stringResource(id = R.string.add_city_hint)) },
                    trailingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onEvent(AddCityEvent.OnSearchCity(state.search))
                            }
                        )
                    },
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                if(state.searching){
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth().height(2.dp))
                }
                Text(
                    text = stringResource(id = R.string.add_city_popular_cities),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(16.dp)
                )
                state.suggestions.forEach {
                    ListItem(
                        headlineContent = { Text(it) },
                        supportingContent = { Text(stringResource(id = R.string.add_city_tap_to_search)) },
                        leadingContent = { Icon(Icons.Filled.Add, contentDescription = null) },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier =
                        Modifier
                            .clickable {
                                onEvent(AddCityEvent.OnSearchChanged(search = it))
                                onEvent(AddCityEvent.OnSearchCity(search = it))
                                expanded = false
                            }
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
        }

        if(state.searching){
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth().height(2.dp))
        }

        if (state.city != null) {
            CityToAddView(
                city = state.city,
                onAdd = { onEvent(AddCityEvent.OnAddCity(state.city)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

    }
}

@Preview(widthDp = 420, heightDp = 300)
@Composable
private fun PreviewAddCityView() {
    FrankiWeatherTheme {
        AddCityView(state = AddCityState(searching = true), onEvent = {}, modifier = Modifier.fillMaxWidth())
    }
}

// City to add

@Composable
fun CityToAddView(
    city: WeatherCity,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        onClick = {
            onAdd()
        },
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(0.6f)) {
                Text(
                    text = city.name,
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 24.dp)
                )
                Text(
                    text = "Confirm to add",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(
                        start = 32.dp,
                        end = 32.dp,
                        top = 8.dp,
                        bottom = 24.dp
                    )
                )
            }
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "add",
                modifier = Modifier
                    .padding(16.dp)
                    .size(52.dp)
                    .weight(0.4f)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCityToAddView() {
    FrankiWeatherTheme {
        CityToAddView(
            onAdd = {},
            city = WeatherCity(
                name = "New York"
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}