package com.example.flightsearch.data

import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    fun getAllAirportsFrom(query: String): Flow<List<Airport>>
    fun getAirportByIataCode(iataCode: String): Flow<Airport>
    fun getAirportById(id: Int): Flow<Airport>

    fun getAllFavoritesFlights(): Flow<List<Favorite>>
    suspend fun insertFavoriteFlight(flight: Favorite)
    suspend fun deleteFavoriteFlight(flight: Favorite)

    fun getSingleFavoriteFlight(departureCode: String, destinationCode: String): Flow<Favorite>
    fun getAllAirports(): Flow<List<Airport>>
    fun deleteFavoriteFlight(departureCode: String, destinationCode: String)
}