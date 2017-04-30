package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon.android.extensions.async
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.rx.RxStatuses
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class TimelineInteractor
@Inject
constructor(
        val timeline: StatusFetcher,
        val rxStatuses: RxStatuses
)
    : TimelineContract.Interactor {

    var out: TimelineContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: TimelineContract.InteractorOutput) {
        this.out = out
    }

    override fun stopInteraction(out: TimelineContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }

    override fun getTimeline(range: Range) {
        disposable = async {
            try {
                out?.onTimeline(timeline.fetch(range).await(), range)
            } catch(e: Throwable) {
                out?.onError(e)
            }
        }
    }

    override fun fav(statusId: Long) {
        disposable = async {
            try {
                rxStatuses.postFavourite(statusId).await()
                out?.onFavResult(true, statusId)
            } catch(e: Throwable) {
                out?.onFavResult(false, statusId)
            }
        }
    }

    override fun unfav(statusId: Long) {
        disposable = async {
            try {
                rxStatuses.postUnfavourite(statusId).await()
                out?.onUnfavResult(true, statusId)
            } catch(e: Throwable) {
                out?.onUnfavResult(false, statusId)
            }
        }
    }

    override fun reblog(statusId: Long) {
        // TODO
    }

    override fun unReblog(statusId: Long) {
        // TODO
    }
}
