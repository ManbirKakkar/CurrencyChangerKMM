package org.dgeek.currencyexchange.framework.di

import org.dgeek.currencyexchange.framework.localstore.LocalStoreImpl
import org.dgeek.currencyexchange.interactor.LocalStore
import org.koin.dsl.module

val localStoreModule = module {
    single<LocalStore> { LocalStoreImpl() }
}