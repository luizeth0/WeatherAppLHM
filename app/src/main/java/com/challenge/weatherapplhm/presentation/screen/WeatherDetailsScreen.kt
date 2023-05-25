package com.challenge.weatherapplhm.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.challenge.weatherapplhm.R
import com.challenge.weatherapplhm.domain.DomainWeather
import com.challenge.weatherapplhm.presentation.components.TextDefault
import com.challenge.weatherapplhm.presentation.viewmodel.WeatherViewModel
import com.challenge.weatherapplhm.utils.UIState

@Composable
fun DetailsScreen(vm: WeatherViewModel) {
    when (val state = vm.weather.collectAsState().value) {
        is UIState.ERROR -> {

        }
        is UIState.LOADING -> {

        }
        is UIState.SUCCESS -> {
            DetailsList(data = state.data)
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsList(data: DomainWeather) {

    val context = LocalContext.current
    val baseImage = "http://openweathermap.org/img/wn/"



    val grade = kelvinToFahrenheit(data.main?.temp ?: 0.0)
    var color = Color.Black
    if (grade >= 77) {
        color = Color.Red
    } else if (grade <= 65) {
        color = Color.Blue
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("WEATHER", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        content = { padding ->


            Column(
                modifier = Modifier.fillMaxSize().padding(padding).background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                TextDefault(
                    modifier = Modifier.padding(8.dp),
                    title = data.name ?: "Name not available",
                    textSize = 32,
                    fontStyle = FontWeight.ExtraBold,
                    textColor = Color.Black
                )



                TextDefault(
                    modifier = Modifier.padding(8.dp),
                    title = "${grade.toString().substringBefore(".")}º",
                    textSize = 50,
                    fontStyle = FontWeight.ExtraBold,
                    textColor = color
                )

                Row {


                    TextDefault(
                        modifier = Modifier.padding(8.dp),
                        title = data.weather?.first()?.main ?: "",
                        textSize = 22,
                        fontStyle = FontWeight.ExtraBold,
                        textColor = Color.Black
                    )

                    TextDefault(
                        modifier = Modifier.padding(8.dp),
                        title = "- ${data.weather?.first()?.description}",
                        textSize = 22,
                        fontStyle = FontWeight.ExtraBold,
                        textColor = Color.Black
                    )


                }

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data("${baseImage}${data.weather?.first()?.icon}@4x.png")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    error = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "ImageIcon",
                    contentScale = ContentScale.Crop, modifier = Modifier.size(150.dp)
                )

            }

        }
    )


}

fun kelvinToFahrenheit(kelvin: Double): Double = (kelvin - 273.15) * 9 / 5 + 32
