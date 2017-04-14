package com.sys1yagi.mastodon.android.extensions

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding

fun <T : ViewDataBinding> Activity.contentViewBinding(layout: Int): Lazy<T> = lazy {
    DataBindingUtil.setContentView<T>(this, layout)
}
