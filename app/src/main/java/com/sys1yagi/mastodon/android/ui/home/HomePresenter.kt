package com.sys1yagi.mastodon.android.ui.home

import com.sys1yagi.mastodon4j.api.entity.Status

class HomePresenter(val view: HomeContract.View, val interactor: HomeContract.Interactor) : HomeContract.Presenter, HomeContract.InteractorOutput {

    val viewModel = HomeViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
        interactor.getPublicTimeline()
    }

    override fun onPause() {
        interactor.stoplInteraction(this)
    }

    override fun onPublicTimeline(statuses: List<Status>) {
        // transform for view
        viewModel.statuses = statuses.map(::TimelineStatus)
        view.showTimeline(viewModel)
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
