package com.sys1yagi.mastodon.android.ui.home.instance.localtimeline

import com.sys1yagi.mastodon4j.rx.RxTimelines
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocalTimelineInteractor
@Inject
constructor(
        val timelines: RxTimelines
) : LocalTimelineContract.Interactor {

    var out: LocalTimelineContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: LocalTimelineContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: LocalTimelineContract.InteractorOutput) {
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
