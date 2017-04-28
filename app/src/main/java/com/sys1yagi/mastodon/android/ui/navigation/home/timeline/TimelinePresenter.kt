package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon.android.ui.navigation.TimelineStatus
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status

class TimelinePresenter
constructor(
        val fragment: Fragment,
        val view: TimelineContract.View,
        val instanceName: String,
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
            interactor.getTimeline(Range(sinceId = viewModel.link?.sinceId))
        }
    }

    override fun onReplyClick(status: Status) {
        router.openTootActivity(fragment, instanceName, status)
    }

    override fun onError(t: Throwable) {
        t.printStackTrace()
        view.showError(t.message ?: "error")
    }

    override fun onTimeline(statuses: Pageable<Status>) {
        viewModel.statuses.addAll(0, statuses.part.map(::TimelineStatus))
        statuses.link?.let {
            viewModel.link = it
        }
        view.showTimeline(viewModel)
    }
}
