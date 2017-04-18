package com.sys1yagi.mastodon.android.extensions

import co.metalab.asyncawait.AsyncController
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

suspend fun <V> AsyncController.await(single: Single<V>): V = this.await {
    single.blockingGet()
}

suspend fun AsyncController.await(completable: Completable): Unit = this.await {
    completable.blockingAwait()
}
