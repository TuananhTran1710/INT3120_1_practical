package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.CategoryData
import com.example.mycity.data.PlaceData
import com.example.mycity.model.CategoryItem
import com.example.mycity.model.CategoryType
import com.example.mycity.model.ListItem
import com.example.mycity.model.PlaceItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PlaceViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PlaceUiState())
    val uiState: StateFlow<PlaceUiState> = _uiState

    fun updateCurrentPlace(selectedPlace: PlaceItem) {
        _uiState.update {
            it.copy(placeItem = selectedPlace)
        }
    }

    fun updateCurrentList(categoryList: List<CategoryItem>) {
        _uiState.update {
            it.copy(list = categoryList)
        }
    }

    fun updatePlaceList(categoryItem: CategoryItem) {
        _uiState.update {
            it.copy(placeList = when (categoryItem.type) {
                CategoryType.Coffee -> PlaceData.getCafeList()
                CategoryType.Restaurant -> PlaceData.getRestaurantList()
                CategoryType.Park -> PlaceData.getParkList()
                CategoryType.Mall -> PlaceData.getMallList()
            })
        }
    }
}

data class PlaceUiState(
    val list: List<ListItem> = CategoryData.getCategoryList(),
    val placeList: List<PlaceItem> = emptyList(),
    val placeItem: PlaceItem? = null,
)