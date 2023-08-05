package org.dgeek.currencyexchange.domain.usecase

import org.dgeek.currencyexchange.db.dao.CurrencyDao
import org.dgeek.currencyexchange.interactor.usecase.FetchCurrencyFromDbUseCase

class FetchCurrencyFromDbUseCaseImpl(private val currencyDao: CurrencyDao) :
    FetchCurrencyFromDbUseCase {
    override suspend fun invoke(): Map<String, Double> {
        return currencyDao.getAllCurrencies()
    }
}