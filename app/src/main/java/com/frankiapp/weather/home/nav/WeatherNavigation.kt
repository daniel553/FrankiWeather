package com.frankiapp.weather.home.nav

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
import com.frankiapp.weather.home.vm.WeatherHomeViewModel
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
            WeatherHomeView(state = state, onEvent = viewModel::onEvent, modifier = Modifier)
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
                WeatherHomeUIEvent.OnAdd -> navController.navigate(WeatherRouter.HomeScreen)
                WeatherHomeUIEvent.OnBack -> navController.popBackStack()
            }
        }.stateIn(this)
    }
}
