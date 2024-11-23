package com.albersa.nooroproject.ui.weather

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albersa.nooroproject.R
import com.albersa.nooroproject.domain.CityWeatherUseCase
import com.albersa.nooroproject.domain.SavedCityUseCase
import com.albersa.nooroproject.domain.model.CityWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityWeatherViewModel @Inject constructor(private  val application: Application, private val savedCityUseCase: SavedCityUseCase, private val cityWeatherUseCase: CityWeatherUseCase): ViewModel() {

    private var _uiState = MutableStateFlow(CityWeatherUiState(false, null, false, false))
    val uiState: StateFlow<CityWeatherUiState> get() = _uiState

    private var _currentWeather = MutableStateFlow<CityWeather?>(null)
    val currentWeather: StateFlow<CityWeather?> get() = _currentWeather

    private var _searchQuery = mutableStateOf("")
    val searchQuery: State<String> get() = _searchQuery

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = uiState.value.copy(isLoading = false, message = com.albersa.nooroproject.ui.models.UserMessage(
            application.getString(
                R.string.something_went_wrong
            ), application.getString(R.string.try_again)))
    }

    init {
        searchForSavedCity()
    }

    fun updateQuery(newText: String) {
        _searchQuery.value = newText
    }

    fun searchByCity(city: String) {
        _uiState.value = CityWeatherUiState(isLoading = true, message = null, showSearchResults = false, showWeather = false)
        viewModelScope.launch(errorHandler) {
            _currentWeather.value = cityWeatherUseCase.getWeatherByCity(city)
            _currentWeather.value?.name?.let { savedCityUseCase.saveCity(it) }
            _uiState.value = uiState.value.copy(isLoading = false, showSearchResults = true)
        }
    }

    private fun searchForSavedCity() {
        _uiState.value = CityWeatherUiState(isLoading = true, message = null, showSearchResults = false, showWeather = false)
        val savedCity = savedCityUseCase.getSavedCity()
        if (!savedCity.isNullOrEmpty()) {
            viewModelScope.launch(errorHandler) {
                _currentWeather.value = cityWeatherUseCase.getWeatherByCity(city = savedCity)
                _uiState.value = uiState.value.copy(isLoading = false, showWeather = true)
            }
        } else {
            _uiState.value = uiState.value.copy(
                isLoading = false,
                message = com.albersa.nooroproject.ui.models.UserMessage(application.getString(R.string.no_city_selected),
                    application.getString(
                        R.string.please_search_for_a_city
                    ))
            )
        }
    }

    fun selectCurrentCity() {
        _searchQuery.value = String()
        _uiState.value = uiState.value.copy(showWeather = true, showSearchResults = false)
    }
}