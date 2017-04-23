package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon.android.ui.navigation.TimelineStatus
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import javax.inject.Inject

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
        if (viewModel.statuses.isEmpty()) {
            view.showProgress()
            interactor.getTimeline()
        } else {
            view.showTimeline(viewModel)
        }
    }

    override fun onPause() {
        interactor.stopInteraction(this)
    }

    override fun refresh() {
        if (!viewModel.statuses.isEmpty()) {
            view.showProgress()
            interactor.getTimeline(Range(sinceId = viewModel.statuses.first().entity.id))
        }
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }

    override fun onTimeline(statuses: List<Status>) {
        viewModel.statuses.addAll(0, statuses.map(::TimelineStatus))
        view.showTimeline(viewModel)
    }
}
