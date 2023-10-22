package dev.hayohtee.flightsearch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.hayohtee.flightsearch.data.model.Airport
import dev.hayohtee.flightsearch.data.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Query("SELECT * FROM airport WHERE (name LIKE '%' || :query || '%') OR iata_code = :query")
    fun searchAirports(query: String): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE iata_code = :iataCode")
    fun getAirport(iataCode: String): Flow<Airport>

    @Query("SELECT * FROM airport WHERE iata_code != :iataCode")
    fun getDestinations(iataCode: String): Flow<List<Airport>>

    @Query("SELECT * FROM favorite")
    fun getFavoriteAirports(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favorite: Favorite)
}