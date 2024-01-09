package com.example.cwtproject.domain.use_case

class FilterCurrencyUseCase {
    fun invoke(currencies: List<String>, query: String): List<String> {
        return currencies.map { it.lowercase() }.filter { it.contains(query.lowercase()) }
    }
}