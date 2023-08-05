package org.dgeek.currencyexchange.framework.datetime

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

actual class DateTime {

    actual companion object {

        actual fun isToday(mills: Long): Boolean {
            return DateUtils.isToday(mills)
        }

        actual fun format(mills: Long, pattern: String): String {
            val formatter = SimpleDateFormat(pattern)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = mills
            return formatter.format(calendar.time)
        }

        actual fun currentTimeMillis(): Long {
            return Calendar.getInstance().timeInMillis
        }
    }
}