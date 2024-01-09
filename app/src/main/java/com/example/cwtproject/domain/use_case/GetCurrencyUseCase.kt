package com.example.cwtproject.domain.use_case

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cwtproject.domain.repository.CurrencyRepository
import com.example.cwtproject.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    fun invoke(): MutableLiveData<Resource<String>> {
        return repository.getCurrencyData()
    }
}