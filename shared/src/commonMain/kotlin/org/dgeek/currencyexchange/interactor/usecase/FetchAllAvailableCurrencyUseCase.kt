package org.dgeek.currencyexchange.interactor.usecase

interface FetchAllAvailableCurrencyUseCase {
    suspend fun invoke(): List<String>
}