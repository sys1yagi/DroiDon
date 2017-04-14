package com.sys1yagi.mastodon4j.api

interface Serializer<T> {
    fun serialize(t: T): String
    fun deserialize(string: String): T
}
