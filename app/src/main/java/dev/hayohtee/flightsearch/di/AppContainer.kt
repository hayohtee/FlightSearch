package dev.hayohtee.flightsearch.di

import android.content.Context
import dev.hayohtee.flightsearch.data.FlightDatabase
import dev.hayohtee.flightsearch.data.FlightRepository
import dev.hayohtee.flightsearch.data.OfflineFlightRepository

interface AppContainer {
    val flightRepository: FlightRepository
}

class AppDataContainer(context: Context): AppContainer {
    override val flightRepository: FlightRepository by lazy {
        OfflineFlightRepository(FlightDatabase.getDatabase(context).flightDao())
    }
}