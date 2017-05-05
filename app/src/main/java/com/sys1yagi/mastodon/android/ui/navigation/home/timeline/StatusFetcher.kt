package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import io.reactivex.Completable
import io.reactivex.Single

interface StatusFetcher {
    enum class Type {
        Home,
        LocalPublic,
        FederatedPublic,
        LocalTag,
        LocalFederated
    }

    fun fetch(range: Range): MastodonRequest<Pageable<Status>>
}
