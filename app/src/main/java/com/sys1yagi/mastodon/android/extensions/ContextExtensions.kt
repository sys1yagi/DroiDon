package com.sys1yagi.mastodon.android.extensions

import android.content.Context
import com.sys1yagi.mastodon.android.MastodonAndroidApplication

fun Context.gson() = (applicationContext as MastodonAndroidApplication).appComponent.provideGson()

