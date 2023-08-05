package org.dgeek.currencyexchange.framework.di

import org.dgeek.currencyexchange.domain.usecase.ConvertCurrencyUseCaseImpl
import org.dgeek.currencyexchange.domain.usecase.FetchAllAvailableCurrencyUseCaseImpl
import org.dgeek.currencyexchange.domain.usecase.FetchCurrencyDataUseCaseImpl
import org.dgeek.currencyexchange.domain.usecase.FetchCurrencyFromDbUseCaseImpl
import org.dgeek.currencyexchange.domain.usecase.FetchLatestCurrencyRateImpl
import org.dgeek.currencyexchange.domain.usecase.SaveCurrencyDataUseCaseImpl
import org.dgeek.currencyexchange.interactor.usecase.ConvertCurrencyUseCase
import org.dgeek.currencyexchange.interactor.usecase.FetchAllAvailableCurrencyUseCase
import org.dgeek.currencyexchange.interactor.usecase.FetchCurrencyDataUseCase
import org.dgeek.currencyexchange.interactor.usecase.FetchCurrencyFromDbUseCase
import org.dgeek.currencyexchange.interactor.usecase.FetchLatestCurrencyRate
import org.dgeek.currencyexchange.interactor.usecase.SaveCurrencyDataUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<FetchAllAvailableCurrencyUseCase> { FetchAllAvailableCurrencyUseCaseImpl(get()) }
    single<FetchCurrencyFromDbUseCase> { FetchCurrencyFromDbUseCaseImpl(get()) }
    single<FetchLatestCurrencyRate> { FetchLatestCurrencyRateImpl(get(), get(), get()) }
    single<SaveCurrencyDataUseCase> { SaveCurrencyDataUseCaseImpl(get()) }
    single<FetchCurrencyDataUseCase> { FetchCurrencyDataUseCaseImpl(get(), get(), get()) }
    single<ConvertCurrencyUseCase> { ConvertCurrencyUseCaseImpl(get()) }
}