package com.challenge.weatherapplhm.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.challenge.weatherapplhm.ui.theme.WeatherAppLHMTheme
import com.challenge.weatherapplhm.presentation.navigation.NavigationGraph
import com.challenge.weatherapplhm.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppLHMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val vm: WeatherViewModel = hiltViewModel()

                    val navHost = rememberNavController()

                    NavigationGraph(navHostController = navHost, vm = vm)

                }
            }
        }
    }
}

