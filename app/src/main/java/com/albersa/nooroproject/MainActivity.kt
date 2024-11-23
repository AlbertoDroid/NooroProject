package com.albersa.nooroproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.albersa.nooroproject.data.models.Condition
import com.albersa.nooroproject.data.models.Current
import com.albersa.nooroproject.data.models.Location
import com.albersa.nooroproject.data.models.Weather
import com.albersa.nooroproject.ui.theme.NooroProjectTheme
import com.albersa.nooroproject.ui.weather.CityWeatherViewModel
import com.albersa.nooroproject.ui.weather.SearchBar
import com.albersa.nooroproject.ui.weather.WeatherHomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NooroProjectTheme {
                val viewModel: CityWeatherViewModel = viewModel()
                val uiState by viewModel.uiState.collectAsState()
                val currentWeather by viewModel.currentWeather.collectAsState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        SearchBar(
                            text = viewModel.searchQuery.value,
                            onTextChanged = {
                                newText -> viewModel.updateQuery(newText)
                            },
                            onSearchIconPressed = { newCity ->
                                viewModel.searchByCity(newCity)
                            }
                        )
                    }
                ) { innerPadding ->
                    WeatherHomeScreen(currentWeather,
                        uiState,
                        onCardSelected =  {
                            viewModel.selectCurrentCity()
                        },
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
