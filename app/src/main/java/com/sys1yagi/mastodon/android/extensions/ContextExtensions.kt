package com.sys1yagi.mastodon.android.extensions

import android.content.Context
import android.support.annotation.DimenRes
import android.widget.Toast
import com.sys1yagi.mastodon.android.DroiDonApplication

fun Context.getDimensionPixelSize(@DimenRes resId: Int) = resources.getDimensionPixelSize(resId)

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
