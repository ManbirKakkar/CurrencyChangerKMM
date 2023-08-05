package org.dgeek.currencyexchange.db.shared

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass
import org.dgeek.currencyexchange.db.AppDatabase
import org.dgeek.currencyexchange.db.AppDatabaseQueries
import org.dgeek.currencyexchange.db.Currency

internal val KClass<AppDatabase>.schema: SqlDriver.Schema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), AppDatabase {
  public override val appDatabaseQueries: AppDatabaseQueriesImpl = AppDatabaseQueriesImpl(this,
      driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE Currency (
          |    CURRENCY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
          |    CURRENCY_NAME TEXT NOT NULL,
          |    CURRENCY_VALUE REAL NOT NULL
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class AppDatabaseQueriesImpl(
  private val database: AppDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), AppDatabaseQueries {
  internal val getAllCurrency: MutableList<Query<*>> = copyOnWriteList()

  internal val getAllAvailableCurrency: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> getAllCurrency(mapper: (
    CURRENCY_ID: Long,
    CURRENCY_NAME: String,
    CURRENCY_VALUE: Double
  ) -> T): Query<T> = Query(-1174460460, getAllCurrency, driver, "AppDatabase.sq", "getAllCurrency",
      "SELECT * FROM Currency") { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getDouble(2)!!
    )
  }

  public override fun getAllCurrency(): Query<Currency> = getAllCurrency { CURRENCY_ID,
      CURRENCY_NAME, CURRENCY_VALUE ->
    Currency(
      CURRENCY_ID,
      CURRENCY_NAME,
      CURRENCY_VALUE
    )
  }

  public override fun getAllAvailableCurrency(): Query<String> = Query(-1276847721,
      getAllAvailableCurrency, driver, "AppDatabase.sq", "getAllAvailableCurrency",
      "SELECT CURRENCY_NAME FROM Currency") { cursor ->
    cursor.getString(0)!!
  }

  public override fun insertIntoCurrency(Currency: Currency): Unit {
    driver.execute(-1159230430,
        """INSERT INTO Currency (CURRENCY_NAME, CURRENCY_VALUE) VALUES (?, ?)""", 2) {
      bindString(1, Currency.CURRENCY_NAME)
      bindDouble(2, Currency.CURRENCY_VALUE)
    }
    notifyQueries(-1159230430, {database.appDatabaseQueries.getAllAvailableCurrency +
        database.appDatabaseQueries.getAllCurrency})
  }
}
