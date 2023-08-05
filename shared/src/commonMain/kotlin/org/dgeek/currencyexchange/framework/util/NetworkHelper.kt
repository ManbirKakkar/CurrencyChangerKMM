package org.dgeek.currencyexchange.framework.util

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

suspend inline fun <reified T> HttpClient.getRequest(
    url: String,
    headers: Map<String, Any>? = null,
    query: Map<String, Any>? = null
): T {
    return this.get(url) {
        headers?.forEach {
            header(it.key, it.value)
        }
        query?.forEach {
            parameter(it.key, it.value)
        }
    }.body()
}
