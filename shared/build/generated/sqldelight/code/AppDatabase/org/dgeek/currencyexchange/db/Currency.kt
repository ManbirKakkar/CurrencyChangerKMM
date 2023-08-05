package org.dgeek.currencyexchange.db

import kotlin.Double
import kotlin.Long
import kotlin.String

public data class Currency(
  public val CURRENCY_ID: Long,
  public val CURRENCY_NAME: String,
  public val CURRENCY_VALUE: Double
) {
  public override fun toString(): String = """
  |Currency [
  |  CURRENCY_ID: $CURRENCY_ID
  |  CURRENCY_NAME: $CURRENCY_NAME
  |  CURRENCY_VALUE: $CURRENCY_VALUE
  |]
  """.trimMargin()
}
