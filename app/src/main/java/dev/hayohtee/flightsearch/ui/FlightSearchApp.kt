package dev.hayohtee.flightsearch.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.hayohtee.flightsearch.ui.screen.HomeScreen
import dev.hayohtee.flightsearch.ui.screen.MainViewModel

@Composable
fun FlightSearchApp(modifier: Modifier = Modifier) {
    val viewModel = viewModel<MainViewModel>(factory = MainViewModel.FACTORY)

    HomeScreen(
        uiState = viewModel.uiState,
        onSearchAirportChange = viewModel::searchAirports,
        onAirportSuggestionClick = viewModel::getDestinations,
        modifier = modifier
    )
}