package com.example.cwtproject.presentation


data class CurrencyState(
    val rawData: String = "",
    val currencies: List<String> = mutableListOf(),
    val filteredCurrencies: List<String> = mutableListOf()
)
