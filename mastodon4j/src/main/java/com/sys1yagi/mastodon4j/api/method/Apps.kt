package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#apps
 */
class Apps(private val client: MastodonClient) {
    fun createApp(clientName: String, redirectUris: String = "urn:ietf:wg:oauth:2.0:oob", scope: Scope, website: String? = null) {
        scope.validate()
        val response = client.post("https://${client.instanceName}/api/v1/apps",
                RequestBody.create(
                        MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                        arrayListOf(
                                "client_name=$clientName",
                                "scopes=$scope",
                                "redirect_uris=$redirectUris"
                        ).apply {
                            website?.let {
                                add("website=${it}")
                            }
                        }.joinToString(separator = "&")
                ))

        if(response.isSuccessful){
            println(response.message())
            println(response.body().string())
        }
        else{
            println(response.code())
            println(response.message())
            println(response.body().string())
        }
    }
}
