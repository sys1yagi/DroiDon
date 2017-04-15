package com.sys1yagi.mastodon.android.extensions

import android.view.LayoutInflater
import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.layoutInflator() =
        LayoutInflater.from(context)
