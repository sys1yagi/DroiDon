package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.rx.RxPublic
import io.reactivex.Single

class LocalPublicStatusFetcher(val publicMethod: RxPublic) : StatusFetcher {
    override fun fetch(range: Range): Single<Pageable<Status>> {
        return publicMethod.getLocalPublic(range)
    }
}
