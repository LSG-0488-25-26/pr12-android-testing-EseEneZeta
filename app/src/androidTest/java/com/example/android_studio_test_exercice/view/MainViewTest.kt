package com.example.android_studio_test_exercice.view

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android_studio_test_exercice.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun switch_wifi_canvia_estat() {
        composeTestRule.onNodeWithTag("wifiSwitch").assertIsOn().performClick().assertIsOff()
    }

    @Test
    fun checkboxes_menu_es_comporten_com_esperat() {
        composeTestRule.onNodeWithTag("carnivorCheckbox").assertIsNotEnabled()
        composeTestRule.onNodeWithTag("vegetariaCheckbox").assertIsOff().performClick().assertIsOn()
        composeTestRule.onNodeWithTag("vegaCheckbox").assertIsOff().performClick().assertIsOn()
    }

    @Test
    fun tristate_checkbox_cicle_complet() {
        val node = composeTestRule.onNodeWithTag("triStateCheckbox")
        node.assertIsDisplayed()
        node.performClick() // Passa a Indeterminate
        node.performClick() // Passa a On
        node.assertIsOn()
        node.performClick() // Torna a Off
        node.assertIsOff()
    }

    @Test
    fun radio_buttons_seleccio() {
        composeTestRule.onNodeWithTag("radio_Vinicius").assertIsNotEnabled()
        composeTestRule.onNodeWithTag("radio_Lamine Yamal").assertIsEnabled().performClick()
        composeTestRule.onNodeWithTag("radio_Raphina").assertIsEnabled().performClick()
    }

    @Test
    fun slider_canvia_valor() {
        composeTestRule.onNodeWithTag("volumeSlider").performTouchInput { swipeRight() }
        composeTestRule.onNodeWithText("Volum: 100%").assertIsDisplayed()
    }

    @Test
    fun dropdown_seleccio() {
        composeTestRule.onNodeWithTag("dropdownSelectedText").performClick()
        composeTestRule.onNodeWithTag("dropdown_Opció B").performClick()
        composeTestRule.onNodeWithText("Opció B").assertIsDisplayed()
    }

    @Test
    fun search_i_snackbar() {
        composeTestRule.onNodeWithTag("searchField").performTextInput("kotlin")
        composeTestRule.onNodeWithTag("searchButton").performClick()
        composeTestRule.onNodeWithTag("snackbarText").assertIsDisplayed()
        
        // Test dismiss
        composeTestRule.onNodeWithTag("snackbarDismissButton").performClick()
        composeTestRule.onNodeWithTag("snackbarText").assertDoesNotExist()
    }

    @Test
    fun toggle_button_cycle() {
        composeTestRule.onNodeWithText("Desactivat").assertIsDisplayed()
        composeTestRule.onNodeWithTag("toggleButton").performClick()
        composeTestRule.onNodeWithText("Activat").assertIsDisplayed()
        composeTestRule.onNodeWithTag("toggleButton").performClick()
        composeTestRule.onNodeWithText("Desactivat").assertIsDisplayed()
    }
}
