package com.sys1yagi.mastodon.android.ui.navigation.home.federatedtimeline

import javax.inject.Inject
import android.support.v4.app.Fragment

class FederatedTimelinePresenter
@Inject constructor(
        val fragment: Fragment,
        val view: FederatedTimelineContract.View,
        val interactor: FederatedTimelineContract.Interactor,
        val router: FederatedTimelineContract.Router
) : FederatedTimelineContract.Presenter, FederatedTimelineContract.InteractorOutput {

    val viewModel = FederatedTimelineViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
    }

    override fun onPause() {
        interactor.stopInteraction(this)
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
