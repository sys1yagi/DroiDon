package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.method.Public

class LocalPublicStatusFetcher(val publicMethod: Public) : StatusFetcher {
    override fun fetch(range: Range): MastodonRequest<Pageable<Status>> {
        return publicMethod.getLocalPublic(range)
    }
}
