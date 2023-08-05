package org.dgeek.currencyexchange.domain.usecase

import org.dgeek.currencyexchange.interactor.usecase.ConvertCurrencyUseCase
import org.dgeek.currencyexchange.interactor.usecase.FetchCurrencyDataUseCase
import kotlin.math.roundToInt

class ConvertCurrencyUseCaseImpl(private val fetchCurrencyDataUseCase: FetchCurrencyDataUseCase) :
    ConvertCurrencyUseCase {
    override suspend fun invoke(currency: String, amount: Double): List<Pair<String, Double>> {
        val currencyData = fetchCurrencyDataUseCase.invoke()
        val usdValue = currencyData.getOrThrow()[currency] ?: 1.0
        return currencyData.getOrThrow().filter { it.key != currency }.map { (currency, rate) ->
            currency to roundOffDecimal(amount * (rate / usdValue))
        }
    }

    private fun roundOffDecimal(number: Double): Double {
        return (number * 1000.0).roundToInt() / 1000.0
    }
}