package com.example.flightsearch.ui.screens.flight

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.NavigationDestination
import com.example.flightsearch.R
import com.example.flightsearch.model.Airport

object FlightScreenDestination : NavigationDestination {
    override val route = "flight_screen"
    override val titleRes = R.string.app_name
    const val CODE_ARG = "iataCode"
    val routeWithArgs = "$route/{$CODE_ARG}"
}

@Composable
fun FlightScreen() {
    val viewModel: FlightViewModel = viewModel(factory = FlightViewModel.Factory)
    val uiState = viewModel.uiState.collectAsState()

    val context = LocalContext.current

    Column {
        FlightResults(
            departureAirport = uiState.value.departureAirport,
            destinationList = uiState.value.destinationList.collectAsState(emptyList()).value,
            favoriteList = uiState.value.favoriteList.collectAsState(emptyList()).value,
            onFavoriteClick = { isFavorite: Boolean, departureAirportCode: String, destinationAirportCode: String ->
                viewModel.onFavoriteClick(isFavorite, departureAirportCode, destinationAirportCode)
            }
        )
    }


}