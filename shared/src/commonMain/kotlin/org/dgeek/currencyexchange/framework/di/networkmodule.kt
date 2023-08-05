package org.dgeek.currencyexchange.framework.di

import org.dgeek.currencyexchange.domain.usecase.NetworkClientImpl
import org.dgeek.currencyexchange.framework.network.NetworkService
import org.dgeek.currencyexchange.interactor.usecase.NetworkClient
import org.koin.dsl.module

val networkModule = module {
    single { NetworkService.getClient() }
    single<NetworkClient> { NetworkClientImpl(get()) }
}