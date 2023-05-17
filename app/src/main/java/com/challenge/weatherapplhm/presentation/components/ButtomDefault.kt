package com.challenge.weatherapplhm.presentation.components

import androidx.compose.material.Colors
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.challenge.weatherapplhm.presentation.navigation.Routes
import okhttp3.Route

@Composable
fun ButtonDefault(title: String, modifier: Modifier, navigate:() -> Unit ) {

    Button(modifier = modifier,onClick = {
        navigate()
    }) {
        Text(text = title, textAlign = TextAlign.Center)
    }
}