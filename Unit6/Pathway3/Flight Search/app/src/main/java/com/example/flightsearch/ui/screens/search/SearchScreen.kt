package com.example.flightsearch.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.NavigationDestination
import com.example.flightsearch.R
import com.example.flightsearch.model.Airport

object SearchDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onSelectCode: (String) -> Unit,
) {
    val viewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory)
    val uiState = viewModel.uiState.collectAsState().value

    val searchResult: List<Airport> by viewModel.getAirportsBy(uiState.searchQuery)
        .collectAsState(emptyList())

    Column(modifier = modifier) {
        SearchTextField(
            query = uiState.searchQuery,
            onQueryChange = { viewModel.onQueryChange(it) }
        )
        SearchDetails(
            searchResult = searchResult,
            onSelectCode = onSelectCode
        )
    }
}


