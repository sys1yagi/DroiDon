package com.sys1yagi.mastodon4j

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

open class MastodonClient(
        val instanceName: String,
        val client: OkHttpClient,
        private val gson: Gson
) {

    open fun getSerializer() = gson

    open fun get(url: String): Response {
        val call = client.newCall(
                Request.Builder()
                        .url(url)
                        .get()
                        .build())
        return call.execute()
    }

    open fun post(url: String, body: RequestBody): Response {
        val call = client.newCall(
                Request.Builder()
                        .url(url)
                        .post(body)
                        .build())
        return call.execute()
    }

}
