package com.sys1yagi.mastodon.android.ui.navigation.home.localtimeline

import com.sys1yagi.mastodon4j.api.entity.Status

interface LocalTimelineContract {

    interface View {
        fun showTimeline(viewModel: LocalTimelineViewModel)
        fun showError(message: String)
        fun finish()
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stoplInteraction(out: InteractorOutput) // base
        fun getPublicTimeline()
    }

    interface InteractorOutput {
        fun onPublicTimeline(statuses: List<Status>)
        fun onError(t: Throwable)
    }

    interface Router {

    }
}
