package com.example.cwtproject.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cwtproject.domain.use_case.FilterCurrencyUseCase
import com.example.cwtproject.domain.use_case.GetCurrencyUseCase
import com.example.cwtproject.domain.use_case.MapResponseToListUseCase
import com.example.cwtproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val mapResponseToListUseCase: MapResponseToListUseCase,
    private val filterCurrencyUseCase: FilterCurrencyUseCase
) : ViewModel() {

    private val _state: MutableLiveData<Resource<CurrencyState>> = MutableLiveData()
    val state: LiveData<Resource<CurrencyState>> = _state
    init {
        fetechData()
    }

    fun fetechData() {
        viewModelScope.launch {
            _state.value = Resource.Loading()
            // Instead of accessing .value directly, observe the LiveData
            val result = getCurrencyUseCase.invoke()
            result.observeForever { currencyResource ->
                when (currencyResource) {
                    is Resource.Error -> _state.value = Resource.Error("No data")
                    is Resource.Loading -> {
                        _state.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        val currencies = mapResponseToListUseCase.invoke(currencyResource.data ?: "")

                        _state.value = Resource.Success(CurrencyState(
                            rawData = currencyResource.data ?: "",
                            currencies = currencies,
                            filteredCurrencies = currencies
                        ))
                    }
                }
            }
        }
    }

    fun filter(query: String) {
        _state.value = Resource.Success(_state.value!!.data!!.copy(
            filteredCurrencies = filterCurrencyUseCase.invoke(_state.value!!.data!!.currencies, query)
        ))
    }
}