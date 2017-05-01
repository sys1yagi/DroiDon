package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon.android.data.model.TimelineStatus
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Attachment
import com.sys1yagi.mastodon4j.api.entity.Status

interface TimelineContract {

    companion object {
        const val REQUEST_CODE_TOOT = 0x2516
    }

    interface View {
        fun showTimeline(viewModel: TimelineViewModel)
        fun showProgress()
        fun showError(message: String)
        fun refreshTimelineAfterPost()
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
        fun refresh()
        fun nextPage()
        fun onReplyClick(status: TimelineStatus)
        fun onReblogClick(status: TimelineStatus)
        fun onFavClick(status: TimelineStatus)
        fun onAttachmentClick(position: Int, attachments: List<Attachment>)
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stopInteraction(out: InteractorOutput) // base
        fun getTimeline(range: Range = Range())
        fun fav(statusId: Long)
        fun unfav(statusId: Long)
        fun reblog(statusId: Long)
        fun unReblog(statusId: Long)
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onTimeline(statuses: Pageable<Status>, range: Range)
        fun onFavResult(isSuccess: Boolean, statusId: Long)
        fun onUnfavResult(isSuccess: Boolean, statusId: Long)
        fun onReblogResult(isSuccess: Boolean, statusId: Long)
        fun onUnreblogResult(isSuccess: Boolean, statusId: Long)
    }

    interface Router {
        fun openTootActivity(fragment: Fragment, instanceName: String, replyToStatus: Status?)
    }
}
