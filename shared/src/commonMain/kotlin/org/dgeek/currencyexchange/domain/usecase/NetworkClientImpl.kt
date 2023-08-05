package org.dgeek.currencyexchange.domain.usecase

import io.ktor.client.HttpClient
import org.dgeek.currencyexchange.framework.util.getRequest
import org.dgeek.currencyexchange.interactor.usecase.NetworkClient
import org.dgeek.currencyexchange.interactor.usecase.data.CurrencyResponse

class NetworkClientImpl(private val client: HttpClient) : NetworkClient {
    override suspend fun getCurrencyList(): CurrencyResponse {
        return client.getRequest(
            url = "latest.json",
            query = mapOf("app_id" to "2c8e8395718a4797bca396470672bd77")
        )
    }
}