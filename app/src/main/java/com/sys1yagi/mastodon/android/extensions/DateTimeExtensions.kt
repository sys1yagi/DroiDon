package com.sys1yagi.mastodon.android.extensions

import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

fun String.toIosZonedDateTime(): ZonedDateTime {
    return ZonedDateTime.parse(this, DateTimeFormatter.ISO_ZONED_DATE_TIME)
}
