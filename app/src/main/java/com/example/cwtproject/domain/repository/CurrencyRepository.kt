package com.example.cwtproject.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.cwtproject.utils.Resource

interface CurrencyRepository {
    fun getCurrencyData(): MutableLiveData<Resource<String>>

}