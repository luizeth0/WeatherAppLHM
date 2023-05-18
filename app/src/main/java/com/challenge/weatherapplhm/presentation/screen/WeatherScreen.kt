package com.challenge.weatherapplhm.presentation.screen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.challenge.weatherapplhm.presentation.components.ButtonDefault
import com.challenge.weatherapplhm.presentation.components.SearchDefault
import com.challenge.weatherapplhm.presentation.navigation.Routes
import com.challenge.weatherapplhm.presentation.viewmodel.WeatherViewModel
import com.google.android.gms.location.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MissingPermission")
@Composable
fun HomeScreen(navController: NavController, vm: WeatherViewModel) {
    val context = LocalContext.current

    var locationPermissionGranted by remember { mutableStateOf(false) }
    var currentLocation by remember { mutableStateOf<Location?>(null) }

    val city = vm.city.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("WEATHER", fontWeight = FontWeight.Bold)},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        content = {padding ->



            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding), horizontalAlignment = Alignment.CenterHorizontally) {

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

                    if (locationPermissionGranted) {
                        getLocation(context) { location ->
                            currentLocation = location
                            vm.getWeatherLocation(location.latitude, location.longitude)
                            navController.navigate(route = Routes.WeatherDetails.route)
                            Log.d("asdas", "HomeScreen: ${location.latitude} ${location.longitude}")
                        }
                    } else {
                        // Request location permission
                        ActivityCompat.requestPermissions(
                            context as Activity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            PERMISSION_REQUEST_CODE
                        )
                    }


                }

            }

        }
    )
    


    LaunchedEffect(context) {
        // Check if location permission is already granted
        locationPermissionGranted = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

}



private const val PERMISSION_REQUEST_CODE = 123
@SuppressLint("MissingPermission")

private fun getLocation(context: Context, callback: (Location) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    val locationRequest = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationAvailability(locationAvailability: LocationAvailability) {
            if (locationAvailability.isLocationAvailable) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        callback(location)
                    }
                }
            }
        }
    }

    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }
}