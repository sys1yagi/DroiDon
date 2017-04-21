package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon.android.ui.navigation.home.localtimeline.LocalTimelineViewModel
import com.sys1yagi.mastodon4j.api.entity.Status

interface TimelineContract {

    interface View {
        fun showTimeline(viewModel: TimelineViewModel)
        fun showError(message: String)
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stopInteraction(out: InteractorOutput) // base
        fun getTimeline()
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onTimeline(statuses: List<Status>)
    }

    interface Router {

    }
}
