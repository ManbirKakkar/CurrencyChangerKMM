package org.dgeek.currencyexchange.framework.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import org.dgeek.currencyexchange.framework.util.JsonHelper

object NetworkService {
    private const val baseUrl = "https://openexchangerates.org/api/"

    fun getClient(): HttpClient {
        return HttpClient {
            expectSuccess = true

            engine {
                threadsCount = 4
                pipelining = true
            }
            install(ContentNegotiation) {
                json(JsonHelper.json)
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            defaultRequest {
                url(baseUrl)
            }
        }
    }

}