package com.sys1yagi.mastodon4j

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

open class MastodonClient(val client: OkHttpClient) {

    fun get() {

    }

    open fun post(url: String, body: RequestBody): Response {
        println(url)
        val call = client.newCall(
                Request.Builder()
                        .url(url)
                        .post(body)
                        .build())
        return call.execute()
    }

}
