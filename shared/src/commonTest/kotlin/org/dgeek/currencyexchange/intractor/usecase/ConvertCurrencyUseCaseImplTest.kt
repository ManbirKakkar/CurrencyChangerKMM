package org.dgeek.currencyexchange.intractor.usecase

import kotlinx.coroutines.runBlocking
import org.dgeek.currencyexchange.domain.usecase.ConvertCurrencyUseCaseImpl
import org.dgeek.currencyexchange.interactor.usecase.FetchCurrencyDataUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ConvertCurrencyUseCaseImplTest {

    @Test
    fun `test convert currency`() = runBlocking {
        // Given
        val currency = "USD"
        val amount = 100.0
        val expected = listOf("EUR" to 90.0, "JPY" to 11000.0)

        val fetchCurrencyDataUseCase = object : FetchCurrencyDataUseCase {
            override suspend fun invoke(): Result<Map<String, Double>> {
                return Result.success(
                    mapOf(
                        "USD" to 1.0,
                        "EUR" to 0.9,
                        "JPY" to 110.0
                    )
                )
            }
        }

        // When
        val result = ConvertCurrencyUseCaseImpl(fetchCurrencyDataUseCase).invoke(currency, amount)

        // Then
        assertEquals(expected, result)
    }

    @Test
    fun `test convert currency when currency data is failure`() = runBlocking {
        // Given
        val currency = "USD"
        val amount = 100.0

        val fetchCurrencyDataUseCase = object : FetchCurrencyDataUseCase {
            override suspend fun invoke(): Result<Map<String, Double>> {
                return Result.failure(Exception("Error fetching currency data"))
            }
        }

        val convertCurrencyUseCaseImpl = ConvertCurrencyUseCaseImpl(fetchCurrencyDataUseCase)
        try {
            convertCurrencyUseCaseImpl.invoke(currency, amount)
            assertTrue { false }
        } catch (e: Exception) {
            assertTrue { true }
        }
    }
}
