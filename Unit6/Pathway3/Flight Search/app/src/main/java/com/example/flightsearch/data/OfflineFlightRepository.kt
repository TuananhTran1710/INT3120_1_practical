package com.example.flightsearch.data

import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import kotlinx.coroutines.flow.Flow

class OfflineFlightRepository(private val flightDao: FlightDao) : FlightRepository {
    override fun getAllAirportsFrom(query: String): Flow<List<Airport>> {
        return flightDao.getAllAirportsFrom(query)
    }

    override fun getAirportByIataCode(iataCode: String): Flow<Airport> {
        return flightDao.getAirportByIataCode(iataCode)
    }

    override fun getAirportById(id: Int): Flow<Airport> {
        return flightDao.getAirportById(id)
    }

    override fun getAllFavoritesFlights(): Flow<List<Favorite>> {
        return flightDao.getAllFavorites()
    }

    override suspend fun insertFavoriteFlight(flight: Favorite) {
        return flightDao.insertFavoriteFlight(flight)
    }

    override suspend fun deleteFavoriteFlight(flight: Favorite) {
        return flightDao.deleteFavoriteFlight(flight)
    }

    override fun deleteFavoriteFlight(departureCode: String, destinationCode: String) {
        return flightDao.deleteFavoriteFlight(departureCode, destinationCode)
    }

    override fun getSingleFavoriteFlight(
        departureCode: String,
        destinationCode: String,
    ): Flow<Favorite> {
        return flightDao.getSingleFavourite(departureCode, destinationCode)
    }

    override fun getAllAirports(): Flow<List<Airport>> {
        return flightDao.getAllAirports()
    }
}