package dev.hayohtee.flightsearch.data

import dev.hayohtee.flightsearch.data.model.Airport
import dev.hayohtee.flightsearch.data.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    suspend fun searchAirports(query: String): Flow<List<Airport>>
    suspend fun getAirport(iataCode: String): Flow<Airport>
    suspend fun getDestinations(iataCode: String): Flow<List<Airport>>
    suspend fun getFavoriteAirports(): Flow<List<Favorite>>
    suspend fun saveFavourite(favorite: Favorite)
}