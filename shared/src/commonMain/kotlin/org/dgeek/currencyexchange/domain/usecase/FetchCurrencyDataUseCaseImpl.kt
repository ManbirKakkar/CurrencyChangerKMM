package org.dgeek.currencyexchange.domain.usecase

import org.dgeek.currencyexchange.framework.datetime.DateTime
import org.dgeek.currencyexchange.interactor.LocalStore
import org.dgeek.currencyexchange.interactor.usecase.FetchCurrencyDataUseCase
import org.dgeek.currencyexchange.interactor.usecase.FetchCurrencyFromDbUseCase
import org.dgeek.currencyexchange.interactor.usecase.FetchLatestCurrencyRate

class FetchCurrencyDataUseCaseImpl(
    private val fetchLatestCurrencyRate: FetchLatestCurrencyRate,
    private val fetchCurrencyFromDbUseCase: FetchCurrencyFromDbUseCase,
    private val localStore: LocalStore
) : FetchCurrencyDataUseCase {
    override suspend fun invoke(): Result<Map<String, Double>> {
        val lastFetchTime = localStore.getValue("timestamp", -1L)
        return when {
            lastFetchTime != -1L && DateTime.currentTimeMillis() - lastFetchTime > (30 * 60 * 1000) -> {
                val result = fetchLatestCurrencyRate.invoke()
                if (result.isSuccess) result
                else {
                    Result.success(fetchCurrencyFromDbUseCase.invoke())
                }
            }

            fetchCurrencyFromDbUseCase.invoke().isEmpty() -> {
                fetchLatestCurrencyRate.invoke()
            }

            else -> {
                Result.success(fetchCurrencyFromDbUseCase.invoke())
            }
        }
    }
}