package org.dgeek.currencyexchange.domain.usecase

import org.dgeek.currencyexchange.db.dao.CurrencyDao
import org.dgeek.currencyexchange.interactor.usecase.SaveCurrencyDataUseCase

class SaveCurrencyDataUseCaseImpl(private val currencyDao: CurrencyDao) : SaveCurrencyDataUseCase {
    override suspend fun invoke(input: Map<String, Double>) {
        currencyDao.insertCurrency(input)
    }
}