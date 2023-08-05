package org.dgeek.currencyexchange.db.dao

import org.dgeek.currencyexchange.db.AppDatabaseQueries
import org.dgeek.currencyexchange.db.Currency

class CurrencyDao(private val appDatabaseQueries: AppDatabaseQueries) {
    fun insertCurrency(currency: Map<String, Double>) {
        appDatabaseQueries.transaction {
            currency.forEach { (key, value) ->
                appDatabaseQueries.insertIntoCurrency(Currency(0, key, value))
            }
        }
    }

    fun getAllCurrencies(): Map<String, Double> {
        return appDatabaseQueries.transactionWithResult {
            val currency = mutableMapOf<String, Double>()
            appDatabaseQueries.getAllCurrency().executeAsList().forEach {
                currency[it.CURRENCY_NAME] = it.CURRENCY_VALUE
            }
            return@transactionWithResult currency
        }
    }

    fun getAvailableCurrency(): List<String> {
        return appDatabaseQueries.transactionWithResult {
            return@transactionWithResult appDatabaseQueries.getAllAvailableCurrency()
                .executeAsList()
        }
    }
}