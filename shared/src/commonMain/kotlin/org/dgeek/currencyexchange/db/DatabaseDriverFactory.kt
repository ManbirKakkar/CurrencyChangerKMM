package org.dgeek.currencyexchange.db

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory() {
    fun createDriver(): SqlDriver
}