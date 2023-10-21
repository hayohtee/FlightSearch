package dev.hayohtee.flightsearch.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.hayohtee.flightsearch.FlightSearchApplication
import dev.hayohtee.flightsearch.data.FlightRepository
import dev.hayohtee.flightsearch.data.model.Airport
import kotlinx.coroutines.launch

class MainViewModel(private val flightRepository: FlightRepository) : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState())
        private set

    fun searchAirports(query: String) {
        uiState = uiState.copy(airportSearchText = query, showDestinations = false)
        if (query.isNotBlank()) {
            viewModelScope.launch {
                flightRepository.searchAirports(query).collect {
                    uiState = uiState.copy(airportSuggestions = it)
                }
            }
        } else {
            uiState = uiState.copy(airportSuggestions = emptyList())
        }
    }

    fun getDestinations(airport: Airport) {
        viewModelScope.launch {
            flightRepository.getDestinations(airport.iataCode).collect {
                uiState = uiState.copy(
                    destinations = it,
                    airportSearchText = airport.iataCode,
                    selectedAirport = airport,
                    showDestinations = true
                )
            }
        }
    }

    companion object {
        val FACTORY: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                MainViewModel(application.container.flightRepository)
            }
        }
    }
}