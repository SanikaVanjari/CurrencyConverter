package com.practice.currencyconverter.di

import com.practice.currencyconverter.data.CurrencyApi
import com.practice.currencyconverter.main.DefaultMainRepository
import com.practice.currencyconverter.main.MainRepository
import com.practice.currencyconverter.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
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

    @Singleton
    @Provides
    fun providesMainRepository(api: CurrencyApi): MainRepository = DefaultMainRepository(api)

    @Singleton
    @Provides
    fun providesDispatchers() : DispatcherProvider = object : DispatcherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}