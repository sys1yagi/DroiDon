package com.sys1yagi.mastodon.android.ui.main

import com.sys1yagi.mastodon4j.rx.RxTimelines
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

class MainInteractor(val timelines: RxTimelines) : MainContract.Interactor {

    var out: MainContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: MainContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: MainContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }

    override fun getPublicTimeline() {
        disposable = timelines.public()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            out?.onPublicTimeline(it)
                        },
                        {
                            out?.onError(it)
                        }
                )
    }

}
