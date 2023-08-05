package org.dgeek.currencyexchange.interactor.usecase

interface ConvertCurrencyUseCase {
    suspend fun invoke(currency: String, amount: Double): List<Pair<String, Double>>
}