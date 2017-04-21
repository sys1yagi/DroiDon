package com.sys1yagi.mastodon4j

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.api.entity.auth.AppRegistration
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

open class MastodonClient(
        private val instanceName: String,
        private val client: OkHttpClient,
        private val gson: Gson,
        private val accessToken: String? = null
) {

    val baseUrl = "https://${instanceName}/api/v1/"

    open fun getSerializer() = gson

    open fun getInstanceName() = instanceName

    open fun get(path: String, parameter: Parameter? = null): Response {
        val url = "$baseUrl/$path"
        val urlWithParams = parameter?.let {
            "$url?${it.build()}"
        } ?: url
        val call = client.newCall(
                authorizationHeader(Request.Builder())
                        .url(urlWithParams)
                        .get()
                        .build())
        return call.execute()
    }

    open fun postUrl(url: String, body: RequestBody): Response {
        val call = client.newCall(
                authorizationHeader(Request.Builder())
                        .url(url)
                        .post(body)
                        .build())
        return call.execute()
    }

    open fun post(path: String, body: RequestBody) =
            postUrl("$baseUrl/$path", body)

    fun authorizationHeader(builder: Request.Builder) = builder.apply {
        accessToken?.let {
            header("Authorization", String.format("Bearer %s", it));
        }
    }

}
