package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#tag
 */
class Tag {
    @SerializedName("name")
    private val name:String = ""

    @SerializedName("url")
    private val url:String = ""
}
