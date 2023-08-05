package org.dgeek.currencyexchange.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.dgeek.currencyexchange.framework.dihelper.CurrencyHelper
import org.dgeek.currencyexchange.interactor.usecase.ConvertCurrencyUseCase
import org.dgeek.currencyexchange.interactor.usecase.FetchAllAvailableCurrencyUseCase
import org.dgeek.currencyexchange.interactor.usecase.FetchCurrencyDataUseCase
import org.dgeek.currencyexchange.ui.component.CustomDropdown
import org.dgeek.currencyexchange.ui.component.Indicator


@Composable
fun CurrencyExchangeScreen(
    currencyHelper: CurrencyHelper = CurrencyHelper(),
) {
    val coroutineScope = rememberCoroutineScope()
    var availableCurrencyList by remember {
        mutableStateOf(listOf<String>())
    }
    var error: String by remember {
        mutableStateOf("")
    }
    var convertedCurrency by remember {
        mutableStateOf(listOf<Pair<String, Double>>())
    }
    var isCurrencyDataFetched by remember {
        mutableStateOf(false)
    }
    var selectedCurrency by remember {
        mutableStateOf("")
    }
    Surface(modifier = Modifier.fillMaxSize().background(Color.White)) {
        var amount by remember {
            mutableStateOf(0.0)
        }
        Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            if (isCurrencyDataFetched) {
                Column(modifier = Modifier.fillMaxSize()) {
                    TextField(
                        value = amount.toString(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            amount = it.toDouble()
                            if (amount > 0) {
                                coroutineScope.launch {
                                    convertedCurrency =
                                        currencyHelper.convertCurrencyUseCase.invoke(selectedCurrency, amount)
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(modifier = Modifier.fillMaxWidth().align(Alignment.End)) {
                        CustomDropdown(availableCurrencyList) { currency ->
                            coroutineScope.launch {
                                selectedCurrency = currency
                                convertedCurrency =
                                    currencyHelper.convertCurrencyUseCase.invoke(currency, amount)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                        convertedCurrency.forEach { (currency, value) ->
                            item {
                                Column(
                                    modifier = Modifier.fillMaxSize()
                                        .background(Color.White)
                                        .padding(8.dp)
                                        .align(Alignment.CenterHorizontally)
                                ) {
                                    Text(text = currency, modifier = Modifier.padding(8.dp))
                                    Text(text = value.toString())
                                }
                            }
                        }
                    }

                }
            } else {
                Row(modifier = Modifier.align(Alignment.Center)) {
                    Indicator()
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Fetching data...")
                }
            }
            if (error.isNotEmpty())
                Text(
                    text = error,
                    modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth()
                        .background(Color.LightGray)
                )
        }
    }

    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            val currencyData = currencyHelper.fetchCurrencyDataUseCase.invoke()
            if (currencyData.isSuccess) {
                availableCurrencyList = currencyHelper.fetchAllAvailableCurrencyUseCase.invoke()
                isCurrencyDataFetched = true
            } else {
                error = currencyData.exceptionOrNull()?.message ?: "Something went wrong"
                coroutineScope.launch {
                    delay(3000)
                    error = ""
                }
            }
        }
    }
}
