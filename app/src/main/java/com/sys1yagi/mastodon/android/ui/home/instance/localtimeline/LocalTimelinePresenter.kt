package com.sys1yagi.mastodon.android.ui.home.instance.localtimeline

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon.android.ui.home.HomeViewModel
import com.sys1yagi.mastodon.android.ui.home.TimelineStatus
import com.sys1yagi.mastodon4j.api.entity.Status
import javax.inject.Inject

class LocalTimelinePresenter
@Inject
constructor(
        val fragment: Fragment,
        val view: LocalTimelineContract.View,
        val interactor: LocalTimelineContract.Interactor,
        val router: LocalTimelineContract.Router
)
    : LocalTimelineContract.Presenter, LocalTimelineContract.InteractorOutput {

    val viewModel = LocalTimelineViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
        interactor.getPublicTimeline()
    }

    override fun onPause() {
        interactor.stoplInteraction(this)
    }

    override fun onError(t: Throwable) {

    }

    override fun onPublicTimeline(statuses: List<Status>) {
        // transform for view
        viewModel.statuses = statuses.map(::TimelineStatus)
        view.showTimeline(viewModel)
    }
}

