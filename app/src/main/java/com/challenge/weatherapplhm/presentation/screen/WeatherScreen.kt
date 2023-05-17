package com.challenge.weatherapplhm.presentation.screen

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.challenge.weatherapplhm.presentation.components.ButtonDefault
import com.challenge.weatherapplhm.presentation.components.SearchDefault
import com.challenge.weatherapplhm.presentation.navigation.Routes
import com.challenge.weatherapplhm.presentation.viewmodel.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.flow.collect

private lateinit var fusedLocationClient: FusedLocationProviderClient

@Composable
fun HomeScreen(navController: NavController, vm: WeatherViewModel) {
    val context = LocalContext.current

    val city = vm.city.collectAsState()
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        SearchDefault(vm = vm)

        Spacer(modifier = Modifier.size(8.dp))

        ButtonDefault(
            title = "Search weather", modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            vm.getWeatherCity(city.value)
            navController.navigate(route = Routes.WeatherDetails.route)
        }

        

        ButtonDefault(
            title = "Weather in my location", modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            //GetLocation







        }

    }
}