package dev.hayohtee.flightsearch.ui.screen

import dev.hayohtee.flightsearch.data.model.Airport

data class UiState(
    val airportSearchText: String = "",
    val airportSuggestions: List<Airport> = emptyList(),
    val destinations: List<Airport> = emptyList(),
    val selectedAirport: Airport? = null,
    val showDestinations: Boolean = false
)
