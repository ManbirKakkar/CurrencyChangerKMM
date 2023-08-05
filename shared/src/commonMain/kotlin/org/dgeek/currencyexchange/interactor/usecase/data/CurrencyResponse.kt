package org.dgeek.currencyexchange.interactor.usecase.data

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse(val base: String, val rates: Map<String, Double>)
