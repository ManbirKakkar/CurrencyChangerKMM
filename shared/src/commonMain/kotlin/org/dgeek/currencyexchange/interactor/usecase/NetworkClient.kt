package org.dgeek.currencyexchange.interactor.usecase

import org.dgeek.currencyexchange.interactor.usecase.data.CurrencyResponse

interface NetworkClient {
    suspend fun getCurrencyList(): CurrencyResponse
}