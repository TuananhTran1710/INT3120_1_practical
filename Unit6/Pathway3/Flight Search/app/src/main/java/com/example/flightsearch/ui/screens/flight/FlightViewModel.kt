package com.example.flightsearch.ui.screens.flight

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.FlightRepository
import com.example.flightsearch.model.Favorite
import com.example.flightsearch.model.Flight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlightViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightRepository: FlightRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FlightUiState())
    val uiState: StateFlow<FlightUiState> = _uiState

    private val airportCode: String = savedStateHandle[FlightScreenDestination.CODE_ARG] ?: ""

    fun onFavoriteClick(
        isFavorite: Boolean,
        departureAirportCode: String,
        destinationAirportCode: String,
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (isFavorite) {
                    flightRepository.deleteFavoriteFlight(
                        departureCode = departureAirportCode,
                        destinationCode = destinationAirportCode
                    )
                } else {
                    flightRepository.insertFavoriteFlight(
                        Favorite(
                            departureCode = departureAirportCode,
                            destinationCode = destinationAirportCode
                        )
                    )
                }
                updateFavorites()
            }
        }
    }

    private fun updateFavorites() {
        _uiState.update {
            it.copy(
                favoriteList = flightRepository.getAllFavoritesFlights()
            )
        }
    }

    init {
        viewModelScope.launch {
            processFlightList(airportCode)
        }
    }

    private fun processFlightList(airportCode: String) {
        viewModelScope.launch {
            val favoriteFlights = flightRepository.getAllFavoritesFlights()
            val airports = flightRepository.getAllAirports()
            val departureAirport = flightRepository.getAirportByIataCode(airportCode).first()
            _uiState.update {
                uiState.value.copy(
                    code = airportCode,
                    favoriteList = favoriteFlights,
                    destinationList = airports,
                    departureAirport = departureAirport,
                )
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                val flightRepository = application.container.flightRepository
                FlightViewModel(
                    this.createSavedStateHandle(), flightRepository = flightRepository
                )
            }
        }
    }
}