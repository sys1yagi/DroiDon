package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import io.reactivex.Single

interface StatusFetcher {
    fun fetch(range: Range): Single<Pageable<Status>>

    enum class Type {
        Home,
        LocalPublic,
        FederatedPublic,
        LocalTag,
        LocalFederated
    }
}
