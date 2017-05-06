package com.sys1yagi.mastodon.android.extensions

import com.sys1yagi.mastodon4j.api.Link

fun Link?.merge(link: Link, isNext: Boolean): Link {
    if (this == null) {
        return link
    } else {
        val newMaxId = link.maxId.takeIf { it > 0L && isNext } ?: this.maxId
        val newSinceId = link.sinceId.takeIf { it > 0L && !isNext } ?: this.sinceId
        return Link("", "", "", newMaxId, newSinceId)
    }
}
