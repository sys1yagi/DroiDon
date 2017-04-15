package com.sys1yagi.mastodon.android.ui.main

import android.text.Html
import com.sys1yagi.mastodon4j.api.entity.Status

class TimelineStatus(val entity: Status) {

    @SuppressWarnings("deprecation")
    fun content() =
            Html.fromHtml(entity.content)

    fun elapsed(): String {
        return "3分前"
    }
}
