package org.dgeek.currencyexchange.framework.network

suspend inline fun <reified T> getResult(request: suspend () -> Any?): Result<T> {
    return try {
        val result = request.invoke() as T
        Result.success(result)
    } catch (error: Exception) {
        Result.failure(error)
    }
}
