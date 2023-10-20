package dev.hayohtee.flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.hayohtee.flightsearch.data.model.Airport
import dev.hayohtee.flightsearch.data.model.Favorite

@Database(entities = [Favorite::class, Airport::class], version = 1, exportSchema = false)
abstract class FlightDatabase : RoomDatabase() {
    abstract fun flightDao(): FlightDao

    companion object {
        @Volatile
        private var INSTANCE: FlightDatabase? = null

        fun getDatabase(context: Context): FlightDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, FlightDatabase::class.java, "flight_database")
                    .fallbackToDestructiveMigration()
                    .createFromAsset("flight_search.db")
                    .build()
                    .also {
                        INSTANCE = it
                    }

            }
        }
    }
}