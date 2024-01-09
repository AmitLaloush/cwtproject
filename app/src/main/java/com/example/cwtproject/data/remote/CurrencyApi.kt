package com.example.cwtproject.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyApi {
    @GET("content/worldmate/currencies/currency2008.dat")
    fun getCurrency(): Call<String>
}