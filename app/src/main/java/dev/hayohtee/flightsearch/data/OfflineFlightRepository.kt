package dev.hayohtee.flightsearch.data

import dev.hayohtee.flightsearch.data.model.Airport
import dev.hayohtee.flightsearch.data.model.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class OfflineFlightRepository(private val flightDao: FlightDao): FlightRepository {
    override suspend fun searchAirports(query: String): Flow<List<Airport>> {
        return withContext(Dispatchers.IO) {
            flightDao.searchAirports(query)
        }
    }

    override suspend fun getAirport(iataCode: String): Flow<Airport> {
        return withContext(Dispatchers.IO) {
            flightDao.getAirport(iataCode)
        }
    }

    override suspend fun getDestinations(iataCode: String): Flow<List<Airport>> {
        return withContext(Dispatchers.IO) {
            flightDao.getDestinations(iataCode)
        }
    }

    override suspend fun getFavoriteAirports(): Flow<List<Favorite>> {
        return withContext(Dispatchers.IO) {
            flightDao.getFavoriteAirports()
        }
    }
}