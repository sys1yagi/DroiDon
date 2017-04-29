package com.sys1yagi.mastodon.android.extensions

import android.content.Context
import com.sys1yagi.mastodon.android.DroiDonApplication

fun Context.gson() = (applicationContext as DroiDonApplication).appComponent.provideGson()

