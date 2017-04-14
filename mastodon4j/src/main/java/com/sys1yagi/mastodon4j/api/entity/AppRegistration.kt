package com.sys1yagi.mastodon4j.api.entity

data class AppRegistration(
        val instanceName: String,
        val clientId: String,
        val clientSecret: String,
        val redirectUri: String = ""
)
