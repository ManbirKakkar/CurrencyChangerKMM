package org.dgeek.currencyexchange.interactor.usecase

interface FetchCurrencyDataUseCase {
    suspend fun invoke(): Result<Map<String, Double>>
}