package org.dgeek.currencyexchange.domain.usecase

import org.dgeek.currencyexchange.framework.datetime.DateTime
import org.dgeek.currencyexchange.framework.network.getResult
import org.dgeek.currencyexchange.interactor.LocalStore
import org.dgeek.currencyexchange.interactor.usecase.FetchLatestCurrencyRate
import org.dgeek.currencyexchange.interactor.usecase.NetworkClient
import org.dgeek.currencyexchange.interactor.usecase.SaveCurrencyDataUseCase
import org.dgeek.currencyexchange.interactor.usecase.data.CurrencyResponse

class FetchLatestCurrencyRateImpl(
    private val networkClient: NetworkClient,
    private val localStore: LocalStore,
    private val saveCurrencyDataUseCase: SaveCurrencyDataUseCase
) : FetchLatestCurrencyRate {
    override suspend fun invoke(): Result<Map<String, Double>> {
        val currencyResponse = getResult<CurrencyResponse> { networkClient.getCurrencyList() }
        return if (currencyResponse.isSuccess && currencyResponse.getOrNull() != null) {
            val result = currencyResponse.getOrNull()
            localStore.putValue("timestamp", DateTime.currentTimeMillis())
            result?.rates?.let { saveCurrencyDataUseCase.invoke(it) }
            Result.success(result?.rates ?: mapOf())
        } else
            Result.failure(currencyResponse.exceptionOrNull() ?: Exception("Unknown error"))
    }
}