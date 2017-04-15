package com.sys1yagi.mastodon.android.ui.main

import com.sys1yagi.mastodon4j.api.entity.Status

interface MainContract {
    interface View {
        fun showTimeline(viewModel: MainViewModel)
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
