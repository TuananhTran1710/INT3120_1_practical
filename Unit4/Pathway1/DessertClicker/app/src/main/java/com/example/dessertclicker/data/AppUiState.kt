package com.example.dessertclicker.data

import androidx.annotation.DrawableRes

data class AppUiState(
    var revenue: Int = 0,
    var dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    var currentDessertPrice: Int = Datasource.dessertList[currentDessertIndex].price,
    @DrawableRes var currentDessertImageId: Int = Datasource.dessertList[currentDessertIndex].imageId
)