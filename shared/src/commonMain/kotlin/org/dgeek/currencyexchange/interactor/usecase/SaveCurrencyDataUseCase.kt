package org.dgeek.currencyexchange.interactor.usecase


interface SaveCurrencyDataUseCase {
    suspend fun invoke(input: Map<String,Double>)
}