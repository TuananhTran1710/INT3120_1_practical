package com.example.flightsearch.ui.screens.flight

import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class FlightUiState(
    val code: String = "",
    val favoriteList: Flow<List<Favorite>> = flowOf(emptyList()),
    val destinationList: Flow<List<Airport>> = flowOf(emptyList()),
    val departureAirport: Airport = Airport(),
)
