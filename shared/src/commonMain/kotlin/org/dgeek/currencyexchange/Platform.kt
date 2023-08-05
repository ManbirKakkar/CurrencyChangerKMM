package org.dgeek.currencyexchange

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform