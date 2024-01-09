package com.example.cwtproject.domain.use_case

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cwtproject.utils.Resource

class MapResponseToListUseCase {
    fun invoke(stringData: String): List<String> {
        return stringData.split("\n")
    }
}