package com.sys1yagi.mastodon.android.extensions

import com.sys1yagi.mastodon4j.api.Range

fun Range.isNextPage() = sinceId == null && maxId != null

fun Range.isPrevPage() = sinceId != null && maxId == null

