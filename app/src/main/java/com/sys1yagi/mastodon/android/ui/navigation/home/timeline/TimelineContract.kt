package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status

interface TimelineContract {

    companion object {
        const val REQUEST_CODE_TOOT = 0x2516
    }

    interface View {
        fun showTimeline(viewModel: TimelineViewModel)
        fun showProgress()
        fun showError(message: String)
        fun refresh()
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
        fun openTootActivity(fragment: Fragment, instanceName: String, replyToStatus: Status?)
    }
}
