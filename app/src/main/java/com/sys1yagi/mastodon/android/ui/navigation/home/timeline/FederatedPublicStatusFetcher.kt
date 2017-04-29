package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.rx.RxTimelines
import io.reactivex.Single

class FederatedPublicStatusFetcher(val timelines: RxTimelines) : StatusFetcher {
    override fun fetch(range: Range): Single<Pageable<Status>> {
        return timelines.getFederatedPublic(range)
    }
}
