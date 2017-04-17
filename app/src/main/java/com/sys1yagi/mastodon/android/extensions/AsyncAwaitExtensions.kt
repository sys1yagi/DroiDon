package com.sys1yagi.mastodon.android.extensions

import co.metalab.asyncawait.AsyncController
import io.reactivex.Single

suspend fun <V> AsyncController.await(single: Single<V>): V = this.await {
    single.blockingGet()
}
