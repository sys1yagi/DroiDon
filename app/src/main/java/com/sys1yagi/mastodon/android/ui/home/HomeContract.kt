package com.sys1yagi.mastodon.android.ui.home

import com.sys1yagi.mastodon4j.api.entity.Status

interface HomeContract {
    interface View {
        fun showTimeline(viewModel: HomeViewModel)
        fun showError(message: String)
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base

    }

    interface Interactor {
        fun startInteraction(out:InteractorOutput) // base
        fun stoplInteraction(out:InteractorOutput) // base

        fun getPublicTimeline()
    }

    interface InteractorOutput {
        fun onPublicTimeline(statuses: List<Status>)
        fun onError(t: Throwable)
    }

    interface Router {
        // TODO
    }
}
