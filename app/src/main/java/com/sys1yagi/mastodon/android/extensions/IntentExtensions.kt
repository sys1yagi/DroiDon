package com.sys1yagi.mastodon.android.extensions

import android.content.Intent

inline fun <reified T> Intent.getRequired(name: String): T {
    extras?.get(name).let {
        if (it !is T) {
            throw IllegalArgumentException("Extras don't have a value specified by key $name")
        }
        return it
    }
}

inline fun <reified T> Intent.getOptional(name: String, default: T): T {
    extras?.get(name).let {
        if (it == null) {
            return default
        } else {
            return it as T
        }
    }
}
