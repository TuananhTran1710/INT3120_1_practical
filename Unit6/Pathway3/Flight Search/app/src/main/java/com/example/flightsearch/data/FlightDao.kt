package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteFlight(favoriteFlight: Favorite)

    @Delete
    fun deleteFavoriteFlight(favoriteFlight: Favorite)

    @Query(
        """
            DELETE FROM favorite
            WHERE departure_code = :departureCode AND destination_code = :destinationCode
        """)
    fun deleteFavoriteFlight(departureCode: String, destinationCode: String)

    @Query(
        """
        SELECT * FROM favorite
        WHERE departure_code = :departureCode
          AND destination_code = :destinationCode
        """)
    fun getSingleFavourite(departureCode: String, destinationCode: String): Flow<Favorite>

    @Query(
        """
            SELECT * FROM favorite
        """
    )
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query(
        """
        SELECT * FROM airport
        WHERE iata_code LIKE '%' || :query || '%' OR name LIKE '%' || :query || '%'        
        ORDER BY iata_code,name ASC 
        """
    )
    fun getAllAirportsFrom(query: String): Flow<List<Airport>>

    @Query(
        """
        SELECT * FROM airport
        WHERE iata_code = :iataCode
        """
    )
    fun getAirportByIataCode(iataCode: String): Flow<Airport>

    @Query(
        """
            SELECT * FROM airport
            WHERE id = :id
        """
    )
    fun getAirportById(id: Int): Flow<Airport>

    @Query(
        """
            SELECT * FROM airport
            ORDER BY iata_code ASC
        """
    )
    fun getAllAirports(): Flow<List<Airport>>
}