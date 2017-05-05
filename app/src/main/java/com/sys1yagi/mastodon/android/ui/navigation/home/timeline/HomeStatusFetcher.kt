package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.method.Timelines

class HomeStatusFetcher(val timelines: Timelines) : StatusFetcher {
    override fun fetch(range: Range): MastodonRequest<Pageable<Status>> {
        return timelines.getHome(range)
    }
}
