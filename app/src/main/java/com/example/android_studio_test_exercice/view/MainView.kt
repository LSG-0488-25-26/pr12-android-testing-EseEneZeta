package com.example.android_studio_test_exercice.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_studio_test_exercice.viewmodel.MainViewModel

@Composable
fun MainView(myViewModel: MainViewModel, modifier: Modifier = Modifier) {
    val estatSwitch by myViewModel.estatSwitch.observeAsState(true)
    val esVegetaria by myViewModel.esVegetaria.observeAsState(false)
    val esVega by myViewModel.esVega.observeAsState(false)
    val esCarnivor by myViewModel.esCarnivor.observeAsState(true)
    val triStateStatus by myViewModel.triStateStatus.observeAsState(ToggleableState.Off)
    val selectedOption by myViewModel.selectedOption.observeAsState("Messi")
    val sliderValue by myViewModel.sliderValue.observeAsState(0f)
    val expanded by myViewModel.expanded.observeAsState(false)
    val selectedItem by myViewModel.selectedItem.observeAsState("Opció A")
    val searchText by myViewModel.searchText.observeAsState("")
    val showSnackbar by myViewModel.showSnackbar.observeAsState(false)
    val toggleState by myViewModel.toggleState.observeAsState(false)

    Box(modifier = modifier.fillMaxSize().padding(20.dp, 60.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Activar Wi-Fi: ", modifier = Modifier.fillMaxWidth(0.6f).padding(0.dp, 10.dp), fontSize = 25.sp)
                Switch(
                    checked = estatSwitch,
                    onCheckedChange = { myViewModel.toggleEstatSwitch() },
                    modifier = Modifier.fillMaxWidth(0.4f).testTag("wifiSwitch"),
                    colors = SwitchDefaults.colors(uncheckedThumbColor = Color.LightGray, checkedThumbColor = Color.Black)
                )
            }

            Column(modifier = Modifier.fillMaxWidth().wrapContentSize().padding(0.dp, 20.dp)) {
                Text(text = "Opcions de menú:", fontSize = 25.sp)
                Row(modifier = Modifier.fillMaxWidth().padding(0.dp, 10.dp)) {
                    Text("Carnívor/a", Modifier.align(Alignment.CenterVertically).fillMaxWidth(0.33f))
                    Text("Vegetarià/na", Modifier.align(Alignment.CenterVertically).fillMaxWidth(0.6f))
                    Text("Vegà/na", Modifier.align(Alignment.CenterVertically).fillMaxWidth(1f))
                }
                Row(modifier = Modifier.fillMaxWidth().wrapContentWidth()) {
                    Checkbox(checked = esCarnivor, onCheckedChange = { myViewModel.toggleEsCarnivor() }, modifier = Modifier.fillMaxWidth(0.20f).testTag("carnivorCheckbox"), enabled = false)
                    Checkbox(checked = esVegetaria, onCheckedChange = { myViewModel.toggleEsVegetaria() }, modifier = Modifier.fillMaxWidth(0.33f).testTag("vegetariaCheckbox"))
                    Checkbox(checked = esVega, onCheckedChange = { myViewModel.toggleEsVega() }, modifier = Modifier.fillMaxWidth(0.33f).testTag("vegaCheckbox"))
                }
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text("TriState", fontSize = 20.sp)
                TriStateCheckbox(state = triStateStatus, onClick = { myViewModel.toggleTriStateStatus() }, modifier = Modifier.testTag("triStateCheckbox"))
            }

            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text("Pilota d'Or:", fontSize = 20.sp)
                listOf("Vinicius", "Lamine Yamal", "Raphina").forEach { player ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = (selectedOption == player),
                            onClick = { myViewModel.setSelectedOption(player) },
                            enabled = (player != "Vinicius"),
                            modifier = Modifier.testTag("radio_$player")
                        )
                        Text(player, Modifier.padding(start = 8.dp))
                    }
                }
            }

            Text("Volum: ${sliderValue.toInt()}%")
            Slider(value = sliderValue, onValueChange = { myViewModel.setSliderValue(it) }, valueRange = 0f..100f, modifier = Modifier.testTag("volumeSlider"))

            Box(modifier = Modifier.wrapContentSize()) {
                Text(text = selectedItem, modifier = Modifier.clickable { myViewModel.setExpanded(true) }.testTag("dropdownSelectedText"))
                DropdownMenu(expanded = expanded, onDismissRequest = { myViewModel.setExpanded(false) }) {
                    listOf("Opció A", "Opció B", "Opció C").forEach { option ->
                        DropdownMenuItem(text = { Text(option) }, onClick = { myViewModel.setSelectedItem(option); myViewModel.setExpanded(false) }, modifier = Modifier.testTag("dropdown_$option"))
                    }
                }
            }

            OutlinedTextField(value = searchText, onValueChange = { myViewModel.setSearchText(it) }, label = { Text("Buscar...") }, modifier = Modifier.testTag("searchField"))
            Button(onClick = { myViewModel.performSearch() }, modifier = Modifier.testTag("searchButton")) { Text("Buscar") }

            if (showSnackbar) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Acció completada!", color = Color.Green, modifier = Modifier.weight(1f).testTag("snackbarText"))
                    Button(onClick = { myViewModel.dismissSnackbar() }, modifier = Modifier.testTag("snackbarDismissButton")) { Text("X") }
                }
            }

            Button(onClick = { myViewModel.toggle() }, modifier = Modifier.testTag("toggleButton"), colors = ButtonDefaults.buttonColors(containerColor = if (toggleState) Color.Green else Color.Red)) {
                Text(if (toggleState) "Activat" else "Desactivat")
            }
        }
    }
}
