package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

class Account {

    @SerializedName("id")
    private val id: Long = 0L

    @SerializedName("username")
    private val username: String = ""

    @SerializedName("acct")
    private val acct: String = ""

    @SerializedName("display_name")
    private val display_name: String = ""

    @SerializedName("note")
    private val note: String = ""

    @SerializedName("url")
    private val url: String = ""

    @SerializedName("avatar")
    private val avatar: String = ""

    @SerializedName("header")
    private val header: String = ""

    @SerializedName("locked")
    private val locked: Boolean = false

    @SerializedName("created_at")
    private val createdAt: String = ""

    @SerializedName("followers_count")
    private val followersCount: Int = 0

    @SerializedName("following_count")
    private val followingCount: Int = 0

    @SerializedName("statuses_count")
    private val statusesCount: Int = 0

}
