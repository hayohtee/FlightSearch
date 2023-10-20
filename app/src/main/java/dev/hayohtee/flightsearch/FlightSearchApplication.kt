package dev.hayohtee.flightsearch

import android.app.Application
import dev.hayohtee.flightsearch.di.AppContainer
import dev.hayohtee.flightsearch.di.AppDataContainer

class FlightSearchApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}