package com.sys1yagi.mastodon4j.api

import com.sys1yagi.kmockito.mock
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.method.Apps
import okhttp3.OkHttpClient
import org.junit.Test

class AppsTest {
    @Test
    fun createApp() {
        // MastodonClient(OkHttpClient())
        val client: MastodonClient = mock()
        val apps = Apps(MastodonClient(OkHttpClient()))

        apps.createApp(
                instanceName = "mstdn.jp",
                clientName = "mastodon-android-sys1yagi",
                scope = Scope(Scope.Name.READ)
        )
    }

}
