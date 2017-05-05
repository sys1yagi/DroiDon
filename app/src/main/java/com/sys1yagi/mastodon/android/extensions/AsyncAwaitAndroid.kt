package com.sys1yagi.mastodon.android.extensions

import com.sys1yagi.mastodon4j.MastodonRequest
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

fun <T> async(start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> T) = async(CommonPool, start, block)
fun ui(start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit) = launch(UI, start, block)
suspend fun <T> MastodonRequest<T>.toJob() =
        async {
            execute()
        }

