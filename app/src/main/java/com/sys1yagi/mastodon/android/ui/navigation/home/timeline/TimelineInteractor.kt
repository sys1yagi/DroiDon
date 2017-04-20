package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import io.reactivex.disposables.Disposables
import javax.inject.Inject

class TimelineInteractor
@Inject
constructor(
        // add dependencies
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
}
