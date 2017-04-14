package com.sys1yagi.mastodon4j.api.entity

enum class Visibility(val value: String) {
    Public("public"),
    Unlisted("unlisted"),
    Private("private"),
    Direct("direct")
}
