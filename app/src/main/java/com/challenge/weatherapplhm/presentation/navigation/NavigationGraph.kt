package com.challenge.weatherapplhm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.challenge.weatherapplhm.presentation.screen.DetailsScreen
import com.challenge.weatherapplhm.presentation.screen.HomeScreen
import com.challenge.weatherapplhm.presentation.viewmodel.WeatherViewModel


@Composable
fun NavigationGraph(navHostController: NavHostController, vm: WeatherViewModel) {

    NavHost(navController = navHostController, startDestination = Routes.WeatherScreen.route ){

        composable(Routes.WeatherScreen.route){
            HomeScreen(navHostController, vm)
        }

        composable(Routes.WeatherDetails.route){

            DetailsScreen(vm = vm)
        }
    }

}