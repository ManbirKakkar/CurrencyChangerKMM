package org.dgeek.currencyexchange.framework.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin() {
    startKoin {
        modules(getModuleList())
    }
}

fun getModuleList(): List<Module> {
    return listOf(databaseModule, networkModule, useCaseModule, localStoreModule)
}