package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.entity.auth.AppRegistration
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import okhttp3.MediaType
import okhttp3.RequestBody
import java.net.URLEncoder

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#apps
 */
class Apps(private val client: MastodonClient) {
    fun createApp(clientName: String, redirectUris: String = "urn:ietf:wg:oauth:2.0:oob", scope: Scope, website: String? = null): AppRegistration {
        scope.validate()
        val response = client.post("https://${client.getInstanceName()}/api/v1/apps",
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

        if (response.isSuccessful) {
            val json = response.body().string()
            return client.getSerializer().fromJson(json, AppRegistration::class.java)
                    .apply {
                        instanceName = client.getInstanceName()
                    }
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }

    fun getOAuthUrl(clientId: String, scope: Scope, redirectUri: String = "urn:ietf:wg:oauth:2.0:oob"): String {
        val endpoint = "/oauth/authorize"
        val parameters = listOf(
                "client_id=$clientId",
                "redirect_uri=$redirectUri",
                "response_type=code",
                "scope=$scope"
        ).joinToString(separator = "&")
        return "https://${client.getInstanceName()}$endpoint?${parameters}"
    }
}
