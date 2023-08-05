package org.dgeek.currencyexchange.framework.util

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object JsonHelper {
    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        prettyPrint = true
        encodeDefaults = true
    }

    inline fun <reified T> String.convertResponse(): T{
      return  json.decodeFromString(this)
    }
}