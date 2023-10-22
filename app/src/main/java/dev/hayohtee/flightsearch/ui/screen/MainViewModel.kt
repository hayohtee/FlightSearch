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
import dev.hayohtee.flightsearch.data.model.Favorite
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(private val flightRepository: FlightRepository) : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            flightRepository.getFavoriteAirports().map { favourites ->
                favourites.map { favourite ->
                    val departure = flightRepository.getAirport(favourite.departureCode).first()
                    val destination = flightRepository.getAirport(favourite.destinationCode).first()
                    Pair(departure, destination)
                }
            }.collect {
                uiState = uiState.copy(favouriteRoutes = it)
            }
        }
    }

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

    fun saveFavourite(departureIataCode: String, destinationIataCode: String) {
        viewModelScope.launch {
            flightRepository.saveFavourite(
                Favorite(
                    departureCode = departureIataCode,
                    destinationCode = destinationIataCode
                )
            )
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