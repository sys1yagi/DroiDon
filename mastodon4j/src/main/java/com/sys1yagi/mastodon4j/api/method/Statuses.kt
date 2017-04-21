package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.entity.Card
import com.sys1yagi.mastodon4j.api.entity.Context
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.genericType
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#statuses
 */
class Statuses(val client: MastodonClient) {

    fun getStatus(statusId: Long): Status {
        val response = client.get("statuses/$statusId")
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Status::class.java
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }

    fun getContext(statusId: Long): Context {
        val response = client.get("statuses/$statusId/context")
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Context::class.java
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }

    fun getCard(statusId: Long): Card {
        val response = client.get("statuses/$statusId/card")
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Card::class.java
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }

    fun getRebloggedBy(statusId: Long, maxId: Long? = null, sinceId: Long? = null, limit: Int = 20): List<Account> {
        val response = client.get(
                "statuses/$statusId/reblogged_by",
                Parameter().apply {
                    maxId?.let { append("max_id", it) }
                    sinceId?.let { append("since_id", it) }
                    append("limit", limit)
                }
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    genericType<List<Account>>()
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }

    fun getFavouritedBy(statusId: Long, maxId: Long? = null, sinceId: Long? = null, limit: Int = 20): List<Account> {
        val response = client.get(
                "statuses/$statusId/favourited_by",
                Parameter().apply {
                    maxId?.let { append("max_id", it) }
                    sinceId?.let { append("since_id", it) }
                    append("limit", limit)
                }
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    genericType<List<Account>>()
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }

    /**
     * status: The text of the status
     * in_reply_to_id (optional): local ID of the status you want to reply to
     * media_ids (optional): array of media IDs to attach to the status (maximum 4)
     * sensitive (optional): set this to mark the media of the status as NSFW
     * spoiler_text (optional): text to be shown as a warning before the actual content
     * visibility (optional): either "direct", "private", "unlisted" or "public"
     */
    fun postStatus(
            status: String,
            inReplyToId: Long? = null,
            mediaIds: List<Long>? = null,
            sensitive: Boolean = false,
            spoilerText: String? = null,
            visibility: Status.Visibility = Status.Visibility.Public
    ): Status {
        val parameters = Parameter().apply {
            append("status", status)
            inReplyToId?.let {
                append("in_reply_to_id", it)
            }
            mediaIds?.let {
                append("media_ids", it.joinToString(separator = ","))
            }
            append("sensitive", sensitive)
            spoilerText?.let {
                append("spoiler_text", it)
            }
            append("visibility", visibility.value)
        }.build()

        val response = client.postUrl("statuses",
                RequestBody.create(
                        MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                        parameters
                ))
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Status::class.java
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }

//    DELETE /api/v1/statuses/:id
//    POST /api/v1/statuses/:id/reblog
//    POST /api/v1/statuses/:id/unreblog
//    POST /api/v1/statuses/:id/favourite
//    POST /api/v1/statuses/:id/unfavourite


}
