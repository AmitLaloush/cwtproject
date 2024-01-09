package com.example.cwtproject.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cwtproject.utils.Resource

@Composable
fun CurrencyItems(viewModel: CurrencyViewModel = hiltViewModel()) {
    val state: Resource<CurrencyState>? = viewModel.state.observeAsState().value
    var text  by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {


        when(state) {
            is Resource.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "failed to download conversion")
                }
            }
            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "loading...")
                }
            }
            is Resource.Success -> {

                Column {

                    Row {
                        TextField(

                            value = text,
                            onValueChange = {
                                text = it
                                viewModel.filter(text)
                            }
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(onClick = {
                            viewModel.fetechData()
                        }) {
                            Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
                        }
                    }


                    LazyColumn {
                        items(state.data!!.filteredCurrencies.size) { index ->
                            val item = state.data!!.filteredCurrencies[index]
                            // Here, you can create a composable for each item in the list
                            Text(text = item)
                        }
                    }
                }

            }
            null -> TODO()
        }
    }
}