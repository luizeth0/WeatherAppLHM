package com.challenge.weatherapplhm.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.challenge.weatherapplhm.presentation.viewmodel.WeatherViewModel

@Composable
fun SearchDefault(vm: WeatherViewModel) {
    val state = vm.city.collectAsState()

    OutlinedTextField(
        value = state.value,
        onValueChange = { vm.setCity(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = { Text(text = "Type the city here...") })

}