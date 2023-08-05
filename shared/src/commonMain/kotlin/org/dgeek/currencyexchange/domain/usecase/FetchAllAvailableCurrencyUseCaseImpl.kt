package org.dgeek.currencyexchange.domain.usecase

import org.dgeek.currencyexchange.db.dao.CurrencyDao
import org.dgeek.currencyexchange.interactor.usecase.FetchAllAvailableCurrencyUseCase

class FetchAllAvailableCurrencyUseCaseImpl(private val currencyDao: CurrencyDao) :
    FetchAllAvailableCurrencyUseCase {
    override suspend fun invoke(): List<String> {
        return currencyDao.getAvailableCurrency()
    }
}