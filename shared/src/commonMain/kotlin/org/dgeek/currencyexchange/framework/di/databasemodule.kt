package org.dgeek.currencyexchange.framework.di

import org.dgeek.currencyexchange.db.Database
import org.dgeek.currencyexchange.db.DatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseDriverFactory() }
    single { Database(get()) }
    single { get<Database>().currencyDao }
}