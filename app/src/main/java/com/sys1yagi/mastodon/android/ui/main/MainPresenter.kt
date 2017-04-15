package com.sys1yagi.mastodon.android.ui.main

import com.sys1yagi.mastodon4j.api.entity.Status

class MainPresenter(val view: MainContract.View, val interactor: MainContract.Interactor) : MainContract.Presenter, MainContract.InteractorOutput {

    val viewModel = MainViewModel()

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
