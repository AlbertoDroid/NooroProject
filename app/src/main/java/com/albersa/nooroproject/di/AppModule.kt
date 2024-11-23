package com.albersa.nooroproject.di

import android.app.Application
import com.albersa.nooroproject.data.common.datasources.SharedPreferencesDataSource
import com.albersa.nooroproject.data.network.interceptors.AuthInterceptor
import com.albersa.nooroproject.data.network.services.WeatherApiService
import com.albersa.nooroproject.data.weather.CityWeatherRemoteDataSource
import com.albersa.nooroproject.data.weather.CityWeatherRepository
import com.albersa.nooroproject.domain.CityWeatherUseCase
import com.albersa.nooroproject.domain.SavedCityUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.interceptors().add(loggingInterceptor)
        clientBuilder.interceptors().add(AuthInterceptor())
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(clientBuilder.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesDataSource(application: Application): SharedPreferencesDataSource {
        return SharedPreferencesDataSource(application)
    }

    @Provides
    @Singleton
    fun provideCityWeatherRemoteDataSource(apiService: WeatherApiService): CityWeatherRemoteDataSource {
        return CityWeatherRemoteDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideCityWeatherRepository(sharedPreferencesDataSource: SharedPreferencesDataSource, cityWeatherRemoteDataSource: CityWeatherRemoteDataSource): CityWeatherRepository {
        return CityWeatherRepository(sharedPreferencesDataSource, cityWeatherRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideSavedCityUseCase(cityWeatherRepository: CityWeatherRepository): SavedCityUseCase {
        return SavedCityUseCase(cityWeatherRepository)
    }

    @Provides
    @Singleton
    fun provideCityWeatherUseCase(cityWeatherRepository: CityWeatherRepository): CityWeatherUseCase {
        return CityWeatherUseCase(cityWeatherRepository)
    }
}