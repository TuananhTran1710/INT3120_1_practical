package com.example.flightsearch.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearch.model.Airport

@Composable
fun SearchDetails(
    searchResult: List<Airport>,
    onSelectCode: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        items(searchResult) { airport ->
            AirportData(airport = airport, modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onSelectCode(airport.iataCode)
                })
        }
    }
}

@Composable
fun AirportData(airport: Airport, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
    ) {
        Text(
            text = airport.iataCode,
            modifier = Modifier.padding(
                end = 16.dp
            ),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = airport.name, style = MaterialTheme.typography.bodySmall
        )
    }
}