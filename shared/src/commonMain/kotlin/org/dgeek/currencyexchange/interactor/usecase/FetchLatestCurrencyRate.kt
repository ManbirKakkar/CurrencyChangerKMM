package org.dgeek.currencyexchange.interactor.usecase


interface FetchLatestCurrencyRate {
    suspend fun invoke(): Result<Map<String, Double>>
}