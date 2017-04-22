package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon.android.extensions.async
import com.sys1yagi.mastodon4j.rx.RxTimelines
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class TimelineInteractor
@Inject
constructor(
        val timeline: RxTimelines
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

    override fun getTimeline() {
        // TODO parameters
        disposable = async {
            try {
                out?.onTimeline(timeline.home().await())
            } catch(e: Throwable) {
                out?.onError(e)
            }
        }
    }
}
