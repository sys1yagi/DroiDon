package com.sys1yagi.mastodon.android.ui.home

import android.content.Context
import android.text.Html
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.extensions.toIosZonedDateTime
import com.sys1yagi.mastodon4j.api.entity.Status

class TimelineStatus(val entity: Status) {

    @SuppressWarnings("deprecation")
    fun content() =
            Html.fromHtml(entity.content)

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
