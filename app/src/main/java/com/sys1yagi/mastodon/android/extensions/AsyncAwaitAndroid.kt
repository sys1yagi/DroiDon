package com.sys1yagi.mastodon.android.extensions

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject
import kotlin.coroutines.experimental.*

fun async(
        c: suspend RxAndroidController.() -> Unit
): CompositeDisposable {
    val subscriptions = CompositeDisposable()
    val controller = RxAndroidController(subscriptions)
    c.startCoroutine(
            controller, controller
    )
    return subscriptions
}

class RxAndroidController internal constructor(val subscriptions: CompositeDisposable) : Continuation<Unit> {
    override val context: CoroutineContext
        get() = EmptyCoroutineContext
    val result = AsyncSubject.create<Unit>()
    override fun resumeWithException(exception: Throwable) {
        result.onError(exception)
    }

    override fun resume(data: Unit) {
        result.onNext(data)
        result.onComplete()
    }

    suspend fun <T> Observable<T>.await() = suspendCoroutine<T> { x ->
        this.singleElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithContinuation(x)
    }

    suspend fun <T> Single<T>.await() = suspendCoroutine<T> { x ->
        this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithContinuation(x)
    }

    suspend fun Completable.await() = suspendCoroutine<Unit> { x ->
        this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithContinuation(x)
    }

    private fun Completable.subscribeWithContinuation(x: Continuation<Unit>) {
        subscriptions.add(subscribe({ x.resume(Unit) }, x::resumeWithException))
    }

    private fun <T> Maybe<T>.subscribeWithContinuation(x: Continuation<T>) {
        val subscription = subscribe(x::resume, x::resumeWithException)
        subscriptions.add(subscription)
    }

    private fun <T> Single<T>.subscribeWithContinuation(x: Continuation<T>) {
        val subscription = subscribe(x::resume, x::resumeWithException)
        subscriptions.add(subscription)
    }
}
