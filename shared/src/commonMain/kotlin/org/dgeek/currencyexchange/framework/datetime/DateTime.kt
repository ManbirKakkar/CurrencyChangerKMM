package org.dgeek.currencyexchange.framework.datetime

expect class DateTime() {

    companion object {
        fun isToday(mills: Long): Boolean
        fun format(mills: Long, pattern: String): String
        fun currentTimeMillis(): Long
    }
}