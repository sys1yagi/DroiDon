package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon.android.data.model.TimelineStatus
import com.sys1yagi.mastodon.android.extensions.isNextPage
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

    companion object {
        const val PER_PAGE = 20
    }

    val viewModel = TimelineViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
        if (viewModel.statuses.isEmpty()) {
            view.showProgress()
            interactor.getTimeline(Range(limit = PER_PAGE))
        } else {
            view.showTimeline(viewModel)
        }
    }

    override fun onPause() {
        interactor.stopInteraction(this)
    }

    override fun refresh() {
        if (!viewModel.statuses.isEmpty()) {
            viewModel.refresh()
            interactor.getTimeline(Range(sinceId = viewModel.link?.sinceId, limit = PER_PAGE))
        }
    }

    override fun nextPage() {
        viewModel.link?.maxId?.let {
            interactor.getTimeline(Range(maxId = it, limit = PER_PAGE))
        } ?: run {
            viewModel.isCompleted = true
            view.showTimeline(viewModel)
        }
    }

    override fun onReplyClick(status: TimelineStatus) {
        router.openTootActivity(fragment, instanceName, status.entity)
    }

    override fun onBoostClick(status: TimelineStatus) {

    }

    override fun onFavClick(status: TimelineStatus) {
        interactor.fav(status)
    }

    override fun onFavResult(isSuccess: Boolean, status: TimelineStatus) {

    }

    override fun onError(t: Throwable) {
        // TODO remove print stack trace
        t.printStackTrace()
        view.showError(t.message ?: "error")
    }

    override fun onTimeline(statuses: Pageable<Status>, range: Range) {
        if (range.isNextPage()) {
            viewModel.statuses.addAll(statuses.part.map(::TimelineStatus))
        } else {
            viewModel.statuses.addAll(0, statuses.part.map(::TimelineStatus))
        }
        statuses.link?.let {
            viewModel.mergeLink(it, range.isNextPage())
        }
        view.showTimeline(viewModel)
    }
}
