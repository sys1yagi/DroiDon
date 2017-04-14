package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#mention
 */
class Mention {

    @SerializedName("url")
    private val url: String = ""

    @SerializedName("username")
    private val username: String = ""

    @SerializedName("acct")
    private val acct: String = ""

    @SerializedName("id	")
    private val id: Long = 0

}
