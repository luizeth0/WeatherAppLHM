package com.challenge.weatherapplhm.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.challenge.weatherapplhm.data.database.LocalRepository
import com.challenge.weatherapplhm.domain.CityUseCases
import com.challenge.weatherapplhm.domain.DomainWeather
import com.challenge.weatherapplhm.domain.LocationUseCases
import com.challenge.weatherapplhm.presentation.viewmodel.WeatherViewModel
import com.challenge.weatherapplhm.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.After

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var viewModel: WeatherViewModel
    private var cityUseCases: CityUseCases = mockk(relaxed = true)
    private var locationUseCases: LocationUseCases = mockk(relaxed = true)
    private var localRepository: LocalRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        cityUseCases = mockk()
        viewModel = WeatherViewModel(cityUseCases, locationUseCases, localRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `setCity should update the city value`() {
        // Arrange
        val cityName = "London"

        // Act
        viewModel.setCity(cityName)

        // Assert
        assertEquals(cityName, viewModel.city.value)
    }

    @Test
    fun `getWeatherCity should update the weather value`() {
        val job = testScope.launch {
            // Arrange
            val cityName = "London"
            val domainWeather = DomainWeather(/* weather details */)
            val expectedUIState = UIState.SUCCESS(domainWeather)
            val weatherFlow = MutableStateFlow(expectedUIState)

            coEvery { cityUseCases.invoke(cityName) } returns weatherFlow

            // Act
            viewModel.getWeatherCity(cityName)

            // Assert
            assertEquals(expectedUIState, viewModel.weather.first())
        }
        job.cancel()
    }

    @Test
    fun `getWeatherCity with null cityName should not update the weather value`() {
        val job = testScope.launch {
            // Arrange
            val cityName: String = ""

            // Act
            viewModel.getWeatherCity(cityName)

            // Assert
            assertEquals(UIState.LOADING, viewModel.weather.first())
        }
        job.cancel()

    }


}