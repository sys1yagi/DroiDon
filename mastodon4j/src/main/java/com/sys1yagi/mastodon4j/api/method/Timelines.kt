package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.genericType

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
 */
class Timelines(val client: MastodonClient) {

    fun home(maxId: String? = null, sinceId: String? = null, limit: Int = 20): List<Status> {
        val response = client.get(
                "timelines/home",
                Parameter().apply {
                    maxId?.let { append("max_id", it) }
                    sinceId?.let { append("since_id", it) }
                    append("limit", limit)
                }
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            )
        } else {
            // TODO
            throw Mastodon4jRequestException(response.message())
        }
    }

    fun public(maxId: String? = null, sinceId: String? = null, limit: Int = 20): List<Status> {
        val response = client.get(
                "timelines/public",
                Parameter().apply {
                    maxId?.let { append("max_id", it) }
                    sinceId?.let { append("since_id", it) }
                    append("limit", limit)
                }
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            )
        } else {
            // TODO
            throw Mastodon4jRequestException(response.message())
        }
    }

    fun tag() {

    }
}
