package org.dgeek.currencyexchange.db

import org.dgeek.currencyexchange.db.dao.CurrencyDao

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())

    val currencyDao = CurrencyDao(database.appDatabaseQueries)

}