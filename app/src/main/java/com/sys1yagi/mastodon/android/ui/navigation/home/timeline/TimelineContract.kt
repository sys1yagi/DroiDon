package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.content.Context
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status

interface TimelineContract {

    interface View {
        fun showTimeline(viewModel: TimelineViewModel)
        fun showProgress()
        fun showError(message: String)
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
        fun refresh()
        fun onReplyClick(status: Status)
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stopInteraction(out: InteractorOutput) // base
        fun getTimeline(range: Range = Range())
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onTimeline(statuses: Pageable<Status>)
    }

    interface Router {
        fun openTootActivity(context: Context, instanceName: String, replyToStatus: Status?)
    }
}
