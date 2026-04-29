package com.example.android_studio_test_exercice.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.state.ToggleableState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun toggleEstatSwitch_canvia_el_valor() {
        val viewModel = MainViewModel()

        assertTrue(viewModel.estatSwitch.value == true)
        viewModel.toggleEstatSwitch()
        assertFalse(viewModel.estatSwitch.value == true)
    }

    @Test
    fun toggleEsCarnivor_canvia_el_valor() {
        val viewModel = MainViewModel()

        assertTrue(viewModel.esCarnivor.value == true)
        viewModel.toggleEsCarnivor()
        assertFalse(viewModel.esCarnivor.value == true)
    }

    @Test
    fun toggleEsVegetaria_canvia_el_valor() {
        val viewModel = MainViewModel()

        assertFalse(viewModel.esVegetaria.value == true)
        viewModel.toggleEsVegetaria()
        assertTrue(viewModel.esVegetaria.value == true)
    }

    @Test
    fun toggleEsVega_canvia_el_valor() {
        val viewModel = MainViewModel()

        assertFalse(viewModel.esVega.value == true)
        viewModel.toggleEsVega()
        assertTrue(viewModel.esVega.value == true)
    }

    @Test
    fun toggleTriStateStatus_recorrer_estats_en_ordre() {
        val viewModel = MainViewModel()

        assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.Indeterminate, viewModel.triStateStatus.value)
        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.On, viewModel.triStateStatus.value)
        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
    }

    @Test
    fun setSelectedOption_actualitza_valor() {
        val viewModel = MainViewModel()

        viewModel.setSelectedOption("Raphina")

        assertEquals("Raphina", viewModel.selectedOption.value)
    }

    @Test
    fun setSliderValue_actualitza_valor() {
        val viewModel = MainViewModel()

        viewModel.setSliderValue(68f)

        assertEquals(68f, viewModel.sliderValue.value)
    }

    @Test
    fun setExpanded_actualitza_valor() {
        val viewModel = MainViewModel()

        viewModel.setExpanded(true)

        assertTrue(viewModel.expanded.value == true)
    }

    @Test
    fun setSelectedItem_actualitza_valor() {
        val viewModel = MainViewModel()

        viewModel.setSelectedItem("Opció C")

        assertEquals("Opció C", viewModel.selectedItem.value)
    }

    @Test
    fun setSearchText_actualitza_valor() {
        val viewModel = MainViewModel()

        viewModel.setSearchText("android")

        assertEquals("android", viewModel.searchText.value)
    }

    @Test
    fun performSearch_mostra_snackbar() {
        val viewModel = MainViewModel()

        assertFalse(viewModel.showSnackbar.value == true)
        viewModel.performSearch()
        assertTrue(viewModel.showSnackbar.value == true)
    }

    @Test
    fun dismissSnackbar_amaga_snackbar() {
        val viewModel = MainViewModel()

        viewModel.performSearch()
        assertTrue(viewModel.showSnackbar.value == true)
        viewModel.dismissSnackbar()
        assertFalse(viewModel.showSnackbar.value == true)
    }

    @Test
    fun toggle_canvia_estat_boto() {
        val viewModel = MainViewModel()

        assertFalse(viewModel.toggleState.value == true)
        viewModel.toggle()
        assertTrue(viewModel.toggleState.value == true)
    }
}
