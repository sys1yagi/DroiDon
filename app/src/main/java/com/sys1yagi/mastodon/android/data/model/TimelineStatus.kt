package com.sys1yagi.mastodon.android.data.model

import android.content.Context
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.extensions.toIosZonedDateTime
import com.sys1yagi.mastodon4j.api.entity.Status

class TimelineStatus(val entity: Status) {

    fun content(): String {
        val content = entity.content.replace("<.*?>".toRegex(), "")

        // todo link
        // todo image

        return content
    }

    fun elapsed(context: Context, now: Long): String {
        // TODO time zone

        val createAt = entity.createdAt.toIosZonedDateTime().toInstant().toEpochMilli()
        val elapsed = (now - createAt) / 1000
        return when {
            elapsed < 3 ->
                context.getString(R.string.status_now)
            elapsed < 60 ->
                context.getString(R.string.status_second, elapsed)
            elapsed < 3600 ->
                context.getString(R.string.status_min, elapsed / 60)
            elapsed < 3600 * 24 ->
                context.getString(R.string.status_hour, elapsed / (3600))
            else -> context.getString(R.string.status_day, elapsed / (3600 * 24))
        }
    }
}
