package com.sys1yagi.mastodon4j.rx

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.entity.auth.AppRegistration
import com.sys1yagi.mastodon4j.api.method.Apps
import com.sys1yagi.mastodon4j.api.method.Timelines
import io.reactivex.Single

class RxApps(client: MastodonClient) {
    val apps = Apps(client)

    fun createApp(clientName: String, redirectUris: String = "urn:ietf:wg:oauth:2.0:oob", scope: Scope, website: String? = null): Single<AppRegistration> {
        return Single.create {
            val registration = apps.createApp(clientName, redirectUris, scope, website)
            it.onSuccess(registration)
        }
    }
}
