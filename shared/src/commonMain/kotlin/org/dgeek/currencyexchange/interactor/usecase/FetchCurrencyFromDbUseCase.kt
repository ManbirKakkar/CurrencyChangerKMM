package org.dgeek.currencyexchange.interactor.usecase

interface FetchCurrencyFromDbUseCase {
    suspend fun invoke(): Map<String, Double>
}