package org.dgeek.currencyexchange.framework.datetime

import platform.Foundation.*
import kotlin.math.roundToLong

actual class DateTime {
    actual companion object {
        actual fun isToday(mills: Long) : Boolean {
            val nsDate = NSDate.dateWithTimeIntervalSince1970(mills / 1000.0)
            val formatter = NSDateFormatter()
            formatter.setDateFormat("dd-MM-yyyy")
            formatter.stringFromDate(nsDate)
            return NSCalendar.currentCalendar.isDateInToday(nsDate)
        }

        actual fun format(mills: Long, pattern: String) : String {
            val nsDate = NSDate.dateWithTimeIntervalSince1970(mills / 1000.0)
            val formatter = NSDateFormatter()
            formatter.setDateFormat(pattern)
            return formatter.stringFromDate(nsDate)
        }

        actual fun currentTimeMillis(): Long {
            return NSDate.now.timeIntervalSince1970.toLong() * 1000
        }
    }
}