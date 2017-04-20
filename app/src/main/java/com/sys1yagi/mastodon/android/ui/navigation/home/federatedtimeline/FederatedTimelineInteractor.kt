package com.sys1yagi.mastodon.android.ui.navigation.home.federatedtimeline

import io.reactivex.disposables.Disposables
import javax.inject.Inject

class FederatedTimelineInteractor
@Inject
constructor(
        // add dependencies
)
    : FederatedTimelineContract.Interactor {

    var out: FederatedTimelineContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: FederatedTimelineContract.InteractorOutput) {
        this.out = out
    }

    override fun stopInteraction(out: FederatedTimelineContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }
}
