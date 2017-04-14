package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#status
 */
class Status {

    @SerializedName("id")
    private val id: Long = 0L

    @SerializedName("uri")
    private val uri: String = ""

    @SerializedName("url")
    private val url: String = ""

    @SerializedName("account")
    private val account: Account? = null

    @SerializedName("in_reply_to_id")
    private val inReplyToId: Long? = null

    @SerializedName("in_reply_to_account_id")
    private val inReplyToAccountId: Long? = null

    @SerializedName("reblog")
    private val reblog: Status? = null

    @SerializedName("content")
    private val content: String = ""

    @SerializedName("created_at")
    private val createdAt: String = ""

    @SerializedName("reblogs_count")
    private val reblogsCount: Int = 0

    @SerializedName("favourites_count")
    private val favouritesCount: Int = 0

    @SerializedName("reblogged")
    private val reblogged: Boolean = false

    @SerializedName("favourited")
    private val favourited: Boolean = false

    @SerializedName("sensitive")
    private val sensitive: Boolean = false

    @SerializedName("spoiler_text")
    private val spoilerText: String = ""

    @SerializedName("visibility")
    private val visibility: Visibility = Visibility.Public

    @SerializedName("media_attachments")
    private val mediaAttachments: List<Attachment> = emptyList()

    @SerializedName("mentions")
    private val mentions: List<Mention> = emptyList()

    @SerializedName("tags")
    private val tags: List<Tag> = emptyList()

    @SerializedName("application")
    private val application: Application? = null
}
