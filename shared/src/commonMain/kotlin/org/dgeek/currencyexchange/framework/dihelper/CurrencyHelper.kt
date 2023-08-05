package org.dgeek.currencyexchange.framework.dihelper

import org.dgeek.currencyexchange.interactor.usecase.ConvertCurrencyUseCase
import org.dgeek.currencyexchange.interactor.usecase.FetchAllAvailableCurrencyUseCase
import org.dgeek.currencyexchange.interactor.usecase.FetchCurrencyDataUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CurrencyHelper : KoinComponent {
    val convertCurrencyUseCase: ConvertCurrencyUseCase by inject()
    val fetchAllAvailableCurrencyUseCase: FetchAllAvailableCurrencyUseCase by inject()
    val fetchCurrencyDataUseCase: FetchCurrencyDataUseCase by inject()
}