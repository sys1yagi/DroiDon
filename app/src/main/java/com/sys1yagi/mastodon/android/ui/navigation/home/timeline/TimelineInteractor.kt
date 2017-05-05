package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon.android.extensions.async
import com.sys1yagi.mastodon.android.extensions.toJob
import com.sys1yagi.mastodon.android.extensions.ui
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.method.Statuses
import io.reactivex.disposables.Disposables
import kotlinx.coroutines.experimental.Job
import javax.inject.Inject

class TimelineInteractor
@Inject
constructor(
        val timeline: StatusFetcher,
        val statuses: Statuses
)
    : TimelineContract.Interactor {

    var out: TimelineContract.InteractorOutput? = null
    var disposable = Disposables.empty()
    var job: Job? = null

    override fun startInteraction(out: TimelineContract.InteractorOutput) {
        this.out = out
    }

    override fun stopInteraction(out: TimelineContract.InteractorOutput) {
        disposable.dispose()
        job?.cancel()
        this.out = null
    }

    override fun getTimeline(range: Range) {
        job = async {
            val timeline = timeline.fetch(range).toJob()
            ui {
                try {
                    out?.onTimeline(timeline.await(), range)
                } catch(e: Throwable) {
                    out?.onError(e)
                }
            }
        }
    }

    override fun fav(statusId: Long) {
        job = async {
            val job = statuses.postFavourite(statusId).toJob()
            ui {
                try {
                    job.await()
                    out?.onFavResult(true, statusId)
                } catch (e: Exception) {
                    out?.onFavResult(false, statusId)
                }
            }
        }
    }

    override fun unfav(statusId: Long) {
        job = async {
            val job = statuses.postUnfavourite(statusId).toJob()
            ui {
                try {
                    job.await()
                    out?.onUnfavResult(true, statusId)
                } catch(e: Throwable) {
                    out?.onUnfavResult(false, statusId)
                }
            }
        }
    }

    override fun reblog(statusId: Long) {


    }

    override fun unReblog(statusId: Long) {
        // TODO
    }
}
