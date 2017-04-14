package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#attachment
 */
class Attachment {
    enum class Type(val value: String) {
        Image("image"),
        Video("video"),
        Gifv("gifv")
    }

    @SerializedName("id")
    private val id = 0L

    @SerializedName("type")
    private val type: Type = Type.Image

    @SerializedName("url")
    private val url: String = ""

    @SerializedName("remote_url")
    private val remoteUrl: String = ""

    @SerializedName("preview_url")
    private val previewUrl: String = ""

    @SerializedName("text_url")
    private val textUrl: String = ""

}
