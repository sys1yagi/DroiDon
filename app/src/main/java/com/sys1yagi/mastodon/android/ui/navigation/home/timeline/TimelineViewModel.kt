package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon.android.data.model.TimelineStatus
import com.sys1yagi.mastodon4j.api.Link

class TimelineViewModel {
    var link: Link? = null
        get() = field
        private set(value) {
            field = value
        }

    var statuses: ArrayList<TimelineStatus> = arrayListOf()
    var isLoadFailed = false
    var isCompleted = false

    fun refresh() {
        isLoadFailed = false
        isCompleted = false
    }

    fun mergeLink(link: Link, isNext: Boolean) {
        if (this.link == null) {
            this.link = link
            return
        }
        this.link?.let {
            val newMaxId = link.maxId.takeIf { it > 0L && isNext } ?: it.maxId
            val newSinceId = link.sinceId.takeIf { it > 0L && !isNext } ?: it.sinceId
            this.link = Link("", "", "", newMaxId, newSinceId)
        }
    }
}
