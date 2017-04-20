package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import javax.inject.Inject
import android.support.v4.app.Fragment

class TimelinePresenter
@Inject constructor(
        val fragment: Fragment,
        val view: TimelineContract.View,
        val interactor: TimelineContract.Interactor,
        val router: TimelineContract.Router
) : TimelineContract.Presenter, TimelineContract.InteractorOutput {

    val viewModel = TimelineViewModel()

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
