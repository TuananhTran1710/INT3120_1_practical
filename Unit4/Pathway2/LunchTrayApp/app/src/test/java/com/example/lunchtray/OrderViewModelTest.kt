package com.example.lunchtray


import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.formatPrice
import org.junit.Test
import org.junit.Assert.assertEquals

class OrderViewModelTest {

    val orderViewModel = OrderViewModel()
    val taxRate = 0.08
    @Test
    fun updateEntree_FirstChoice() {
        val mockup = DataSource.entreeMenuItems[0]
        orderViewModel.updateEntree(mockup)
        assertEquals(mockup,orderViewModel.uiState.value.entree)
        assertEquals(mockup.price.formatPrice(),orderViewModel.uiState.value.itemTotalPrice.formatPrice())
        assertEquals((mockup.price*(1+taxRate)).formatPrice(),orderViewModel.uiState.value.orderTotalPrice.formatPrice())
    }

    @Test
    fun updateSideDish_FirstChoice() {
        val mockup = DataSource.sideDishMenuItems[0]
        orderViewModel.updateSideDish(mockup)
        assertEquals(mockup,orderViewModel.uiState.value.sideDish)
        assertEquals(mockup.price.formatPrice(),orderViewModel.uiState.value.itemTotalPrice.formatPrice())
        assertEquals((mockup.price*(1+taxRate)).formatPrice(),orderViewModel.uiState.value.orderTotalPrice.formatPrice())
    }

    @Test
    fun updateAccompaniment_FirstChoice() {
        val mockup = DataSource.accompanimentMenuItems[0]
        orderViewModel.updateAccompaniment(mockup)
        assertEquals(mockup,orderViewModel.uiState.value.accompaniment)
        assertEquals(mockup.price.formatPrice(),orderViewModel.uiState.value.itemTotalPrice.formatPrice())
        assertEquals((mockup.price*(1+taxRate)).formatPrice(),orderViewModel.uiState.value.orderTotalPrice.formatPrice())
    }

    @Test
    fun updateEntree_SecondChoice() {
        val mockup = DataSource.entreeMenuItems[0]
        orderViewModel.updateEntree(mockup)
        val change = DataSource.entreeMenuItems[1]
        orderViewModel.updateEntree(change)
        assertEquals(change,orderViewModel.uiState.value.entree)
        assertEquals(change.price.formatPrice(),orderViewModel.uiState.value.itemTotalPrice.formatPrice())
        assertEquals((change.price*(1+taxRate)).formatPrice(),orderViewModel.uiState.value.orderTotalPrice.formatPrice())
    }

    @Test
    fun updateSideDish_SecondChoice() {
        val mockup = DataSource.sideDishMenuItems[0]
        orderViewModel.updateSideDish(mockup)
        val change = DataSource.sideDishMenuItems[1]
        orderViewModel.updateSideDish(change)
        assertEquals(change,orderViewModel.uiState.value.sideDish)
        assertEquals(change.price.formatPrice(),orderViewModel.uiState.value.itemTotalPrice.formatPrice())
        assertEquals((change.price*(1+taxRate)).formatPrice(),orderViewModel.uiState.value.orderTotalPrice.formatPrice())
    }

    @Test
    fun updateAccompaniment_SecondChoice() {
        val mockup = DataSource.accompanimentMenuItems[0]
        orderViewModel.updateAccompaniment(mockup)
        val change = DataSource.accompanimentMenuItems[1]
        orderViewModel.updateAccompaniment(change)
        assertEquals(change,orderViewModel.uiState.value.accompaniment)
        assertEquals(change.price.formatPrice(),orderViewModel.uiState.value.itemTotalPrice.formatPrice())
        assertEquals((change.price*(1+taxRate)).formatPrice(),orderViewModel.uiState.value.orderTotalPrice.formatPrice())
    }


    @Test
    fun OrderViewModel_MultipleChoice() {
        val entree = DataSource.entreeMenuItems[0]
        val sidedish = DataSource.sideDishMenuItems[3]
        val accompaniment = DataSource.accompanimentMenuItems[2]

        val itemTotalPrice = entree.price + sidedish.price + accompaniment.price
        val orderTotalPrice = itemTotalPrice*(1+taxRate)

        orderViewModel.updateEntree(entree)
        orderViewModel.updateSideDish(sidedish)
        orderViewModel.updateAccompaniment(accompaniment)

        assertEquals(entree,orderViewModel.uiState.value.entree)
        assertEquals(sidedish,orderViewModel.uiState.value.sideDish)
        assertEquals(accompaniment,orderViewModel.uiState.value.accompaniment)
        assertEquals(itemTotalPrice.formatPrice(),orderViewModel.uiState.value.itemTotalPrice.formatPrice())
        assertEquals(orderTotalPrice.formatPrice(),orderViewModel.uiState.value.orderTotalPrice.formatPrice())
    }
}