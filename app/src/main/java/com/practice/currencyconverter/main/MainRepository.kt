package com.practice.currencyconverter.main

import com.practice.currencyconverter.data.CurrencyApi
import com.practice.currencyconverter.data.models.CurrencyResponse
import com.practice.currencyconverter.util.Resource

interface MainRepository {
    suspend fun getRates(base:String): Resource<CurrencyResponse>
}