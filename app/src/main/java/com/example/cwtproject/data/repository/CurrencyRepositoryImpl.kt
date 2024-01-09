package com.example.cwtproject.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cwtproject.data.remote.CurrencyApi
import com.example.cwtproject.domain.repository.CurrencyRepository
import com.example.cwtproject.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyRepositoryImpl(private val api: CurrencyApi): CurrencyRepository {

    val currencyResultLiveData: MutableLiveData<Resource<String>> = MutableLiveData()

    override fun getCurrencyData(): MutableLiveData<Resource<String>> {
        val call = api.getCurrency()
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.code() == 200 && response.body() != null) {
                    val responseBody = response.body()!!
                    currencyResultLiveData.postValue(Resource.Success(responseBody))

                } else {
                    currencyResultLiveData.postValue(Resource.Error("Server error"))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                currencyResultLiveData.postValue(Resource.Error("Server error"))
            }
        })
        return currencyResultLiveData
    }

}