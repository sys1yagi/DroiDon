package com.sys1yagi.mastodon.android.ui.home

import com.sys1yagi.mastodon4j.rx.RxTimelines
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeInteractor
@Inject
constructor(val timelines: RxTimelines)
    : HomeContract.Interactor {

    var out: HomeContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: HomeContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: HomeContract.InteractorOutput) {
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
