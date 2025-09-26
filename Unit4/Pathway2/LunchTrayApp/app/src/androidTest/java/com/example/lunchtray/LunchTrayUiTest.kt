package com.example.lunchtray

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.lunchtray.ui.theme.LunchTrayTheme
import org.junit.Rule
import org.junit.Test

class LunchTrayUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun StartOrderScreen_StartButtonClick_EntreeMenuScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithText("Choose Entree").assertExists("Start Button in Start Order Screen doesn't work")
    }

    @Test
    fun EntreeOrderScreen_BackButtonClick_StartOrderScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mushroom Pasta").performClick()
        composeTestRule.onNodeWithTag("BackButton").performClick()
        composeTestRule.onNodeWithText("Start Order").assertExists("Problem with back button in Entree Screen")
    }

    @Test
    fun EntreeOrderScreen_CancelButtonClick_StartOrderScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mushroom Pasta").performClick()
        composeTestRule.onNodeWithText("CANCEL").performClick()
        composeTestRule.onNodeWithText("Start Order").assertExists("Problem with back button in Entree Screen")
    }

    @Test
    fun EntreeOrderScreen_NextButtonClick_SideDishMenuScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mushroom Pasta").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithText("Choose Side Dish").assertExists("Problem in Entree menu screen")
    }

    @Test
    fun SideDishOrderScreen_NextButtonClick_AccompanimentMenuScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mushroom Pasta").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("radio_button_Summer Salad").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithText("Choose Accompaniment").assertExists("Problem in Side dish menu screen")
    }

    @Test
    fun SideDishOrderScreen_CancelButtonClick_StartOrderScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mushroom Pasta").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithText("CANCEL").performClick()
        composeTestRule.onNodeWithText("Start Order").assertExists("Problem with back button in Choose Side Dish Screen")
    }

    @Test
    fun SideDishOrderScreen_BackButtonClick_EntreeMenuScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mushroom Pasta").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("BackButton").performClick()
        composeTestRule.onNodeWithText("Choose Entree").assertExists("Problem with back button in Side Dish Screen")
    }

    @Test
    fun AccompanimentScreen_NextButtonClick_CheckoutScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mushroom Pasta").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("radio_button_Summer Salad").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mixed Berries").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithText("Order Checkout").assertExists("Problem in Side dish menu screen")
    }

    @Test
    fun AccompanimentScreen_CancelButtonClick_StartOrderScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mushroom Pasta").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("radio_button_Summer Salad").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithText("CANCEL").performClick()
        composeTestRule.onNodeWithText("Start Order").assertExists("Problem with back button in Choose Accompaniment Screen")
    }


    @Test
    fun AccompanimentScreen_BackButtonClick_SideDishOrderScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mushroom Pasta").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("radio_button_Summer Salad").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("BackButton").performClick()
        composeTestRule.onNodeWithText("Choose Side Dish").assertExists("Problem with back button in Accompaniment Screen")
    }

    @Test
    fun OrderCheckout_SubmitButtonClick_StartOrderScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mushroom Pasta").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("radio_button_Summer Salad").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mixed Berries").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithText("SUBMIT").performClick()
        composeTestRule.onNodeWithText("Start Order").assertExists("Problem in Order Checkout")

    }

    @Test
    fun OrderCheckout_BackButtonClick_AccompanimentOrderScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mushroom Pasta").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("radio_button_Summer Salad").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mixed Berries").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("BackButton").performClick()
        composeTestRule.onNodeWithText("Choose Accompaniment").assertExists("Problem with back button in Order Checkout Screen")

    }

    @Test
    fun OrderCheckout_CancelButtonClick_StartOrderScreen() {
        composeTestRule.setContent {
            LunchTrayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LunchTrayApp()
                }
            }
        }

        composeTestRule.onNodeWithText("Start Order").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mushroom Pasta").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("radio_button_Summer Salad").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithTag("radio_button_Mixed Berries").performClick()
        composeTestRule.onNodeWithText("NEXT").performClick()
        composeTestRule.onNodeWithText("CANCEL").performClick()
        composeTestRule.onNodeWithText("Start Order").assertExists("Problem in Order Checkout")

    }



}