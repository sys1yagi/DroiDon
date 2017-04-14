package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.kmockito.mock
import com.sys1yagi.mastodon4j.MastodonClient
import org.junit.Test

class AppsTest {
    @Test
    fun createApp() {
//        val client = MastodonClient("mstdn.jp", OkHttpClient(), Gson())
        val client: MastodonClient = mock()
        val apps = Apps(client)

//        apps.createApp(
//                clientName = "mastodon-android-sys1yagi",
//                scope = Scope(Scope.Name.READ)
//        )
    }

}
