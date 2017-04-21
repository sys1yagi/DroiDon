package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import javax.inject.Inject
import android.support.v4.app.Fragment
import com.sys1yagi.mastodon.android.ui.navigation.TimelineStatus
import com.sys1yagi.mastodon4j.api.entity.Status

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
        interactor.getTimeline()
    }

    override fun onPause() {
        interactor.stopInteraction(this)
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }

    override fun onTimeline(statuses: List<Status>) {
        viewModel.statuses = statuses.map(::TimelineStatus)
        view.showTimeline(viewModel)
    }
}
