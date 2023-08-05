package org.dgeek.currencyexchange.db

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Double
import kotlin.Long
import kotlin.String
import kotlin.Unit

public interface AppDatabaseQueries : Transacter {
  public fun <T : Any> getAllCurrency(mapper: (
    CURRENCY_ID: Long,
    CURRENCY_NAME: String,
    CURRENCY_VALUE: Double
  ) -> T): Query<T>

  public fun getAllCurrency(): Query<Currency>

  public fun getAllAvailableCurrency(): Query<String>

  public fun insertIntoCurrency(Currency: Currency): Unit
}