package com.practice.currencyconverter.di

import com.practice.currencyconverter.data.CurrencyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyApi =
        Retrofit.Builder().baseUrl("https://api.exchangeratesapi.io/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CurrencyApi::class.java)
}