package com.frankiapp.weather.home.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.frankiapp.weather.home.WeatherHomeUIEvent
import com.frankiapp.weather.home.WeatherHomeView
import com.frankiapp.weather.home.WeatherHomeViewModel
import com.frankiapp.weather.home.addcity.AddCityUIEvent
import com.frankiapp.weather.home.addcity.AddCityView
import com.frankiapp.weather.home.addcity.AddCityViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

@Composable
fun WeatherNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = WeatherRouter.HomeScreen.destination,
    ) {
        composable(
            route = WeatherRouter.HomeScreen.destination
        ) {
            val viewModel: WeatherHomeViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            HandleHomeViewUiActions(viewModel, navController)
            WeatherHomeView(
                state = state,
                onEvent = viewModel::onEvent,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(
            route = WeatherRouter.AddScreen.destination
        ) {
            val viewModel: AddCityViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            HandleAddCityUiActions(viewModel, navController)
            AddCityView(
                state = state,
                onEvent = viewModel::onEvent,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun HandleHomeViewUiActions(
    viewModel: WeatherHomeViewModel,
    navController: NavHostController
) {
    LaunchedEffect(viewModel) {
        viewModel.uiEvent.onEach { event ->
            when (event) {
                WeatherHomeUIEvent.OnAdd -> navController.navigate(WeatherRouter.AddScreen.destination)
                WeatherHomeUIEvent.OnBack -> navController.popBackStack()
            }
        }.stateIn(this)
    }
}

@Composable
private fun HandleAddCityUiActions(
    viewModel: AddCityViewModel,
    navController: NavHostController
) {
    LaunchedEffect(viewModel) {
        viewModel.uiEvent.onEach { event ->
            when (event) {
                AddCityUIEvent.OnBack -> navController.popBackStack()
            }
        }.stateIn(this)
    }
}
