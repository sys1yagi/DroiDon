package com.sys1yagi.mastodon.android.extensions

import android.content.Context
import android.support.annotation.DimenRes
import com.sys1yagi.mastodon.android.DroiDonApplication

fun Context.gson() = (applicationContext as DroiDonApplication).appComponent.provideGson()

fun Context.getDimensionPixelSize(@DimenRes resId: Int) = resources.getDimensionPixelSize(resId)
