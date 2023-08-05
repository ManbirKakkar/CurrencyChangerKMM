package org.dgeek.currencyexchange.interactor

interface LocalStore {
   fun <T> putValue(key: String, value: T)
   fun <T> getValue(key: String, default: T): T
   fun clear()
}