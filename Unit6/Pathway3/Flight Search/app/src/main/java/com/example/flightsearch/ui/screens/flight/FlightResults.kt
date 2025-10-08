package com.example.flightsearch.ui.screens.flight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearch.data.MockData
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite

@Composable
fun FlightResults(
    modifier: Modifier = Modifier,
    departureAirport: Airport,
    destinationList: List<Airport>,
    favoriteList: List<Favorite>,
    onFavoriteClick: (Boolean, String, String) -> Unit,
) {
    Column {
        LazyColumn(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            items(destinationList, key = { it.id }) { item ->
                val isFavorite = favoriteList.find { f ->
                    f.departureCode == departureAirport.iataCode && f.destinationCode == item.iataCode
                } != null


                FlightRow(
                    isFavorite = isFavorite,
                    departureAirportCode = departureAirport.iataCode,
                    departureAirportName = departureAirport.name,
                    destinationAirportCode = item.iataCode,
                    destinationAirportName = item.name,
                    onFavoriteClick = { departureAirportCode, destinationAirportCode ->
                        onFavoriteClick(isFavorite, departureAirportCode, destinationAirportCode)
                    },
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun FlightResultsPreview() {
    val mockData = MockData.airports

    FlightResults(departureAirport = mockData[0],
        destinationList = mockData,
        favoriteList = emptyList(),
        onFavoriteClick = { _: Boolean, _: String, _: String -> }
    )
}