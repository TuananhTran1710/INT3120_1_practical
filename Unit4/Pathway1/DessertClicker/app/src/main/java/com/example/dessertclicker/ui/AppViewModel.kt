package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.AppUiState
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _uistate = MutableStateFlow(AppUiState())
    val uiState = _uistate.asStateFlow()

    fun onDessertClicked() {
        // Update the revenue
        _uistate.update { appUiState ->
            val dessertToShow = determineDessertToShow(appUiState.dessertsSold + 1)
            appUiState.copy(
                revenue = appUiState.revenue + appUiState.currentDessertPrice,
                dessertsSold = appUiState.dessertsSold + 1,
                currentDessertImageId = dessertToShow.imageId,
                currentDessertPrice = dessertToShow.price
            )
        }
    }

    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow(
        dessertsSold: Int
    ): Dessert {
        val desserts = Datasource.dessertList
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}