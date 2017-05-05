package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.support.v4.app.Fragment
import com.stfalcon.frescoimageviewer.ImageViewer
import com.sys1yagi.mastodon.android.data.model.TimelineStatus
import com.sys1yagi.mastodon.android.extensions.isNextPage
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Attachment
import com.sys1yagi.mastodon4j.api.entity.Status
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.yield
import timber.log.Timber

class TimelinePresenter
constructor(
        val fragment: Fragment,
        val view: TimelineContract.View,
        val instanceName: String,
        val interactor: TimelineContract.Interactor,
        val router: TimelineContract.Router
) : TimelineContract.Presenter, TimelineContract.InteractorOutput {

    companion object {
        const val PER_PAGE = 20
    }

    val viewModel = TimelineViewModel()

    val statusMap = hashMapOf<Long, TimelineStatus>()

    override fun onResume() {
        interactor.startInteraction(this)
        if (viewModel.statuses.isEmpty()) {
            view.showProgress()
            interactor.getTimeline(Range(limit = PER_PAGE))
        } else {
            view.showTimeline(viewModel)
        }
    }

    override fun onPause() {
        interactor.stopInteraction(this)
    }

    override fun refresh() {
        if (!viewModel.statuses.isEmpty()) {
            viewModel.refresh()
            interactor.getTimeline(Range(sinceId = viewModel.link?.sinceId, limit = PER_PAGE))
        }
    }

    override fun nextPage() {
        viewModel.link?.maxId?.let {
            interactor.getTimeline(Range(maxId = it, limit = PER_PAGE))
        } ?: run {
            viewModel.isCompleted = true
            view.showTimeline(viewModel)
        }
    }

    override fun onReplyClick(status: TimelineStatus) {
        router.openTootActivity(fragment, instanceName, status.entity)
    }

    override fun onReblogClick(status: TimelineStatus) {
        if (status.isReblogged) {
            interactor.reblog(status.entity.id)
        } else {
            interactor.unReblog(status.entity.id)
        }
        view.showTimeline(viewModel)
    }

    override fun onFavClick(status: TimelineStatus) {
        if (status.isFavourited) {
            interactor.fav(status.entity.id)
        } else {
            interactor.unfav(status.entity.id)
        }
        view.showTimeline(viewModel)
    }

    override fun onAttachmentClick(position: Int, attachments: List<Attachment>) {
        ImageViewer.Builder(fragment.context, attachments.map { it.url })
                .setStartPosition(position)
                .show()
    }

    override fun onFavResult(isSuccess: Boolean, statusId: Long) {
        if (!isSuccess) {
            statusMap[statusId]?.let {
                it.isFavourited = false
                view.showTimeline(viewModel)
            }
        }
    }

    override fun onUnfavResult(isSuccess: Boolean, statusId: Long) {
        if (!isSuccess) {
            statusMap[statusId]?.let {
                it.isFavourited = true
                view.showTimeline(viewModel)
            }
        }
    }

    override fun onReblogResult(isSuccess: Boolean, statusId: Long) {
        if (!isSuccess) {
            statusMap[statusId]?.let {
                it.isReblogged = false
                view.showTimeline(viewModel)
            }
        }
    }

    override fun onUnreblogResult(isSuccess: Boolean, statusId: Long) {
        if (!isSuccess) {
            statusMap[statusId]?.let {
                it.isReblogged = true
                view.showTimeline(viewModel)
            }
        }
    }

    override fun onError(t: Throwable) {
        // TODO remove print stack trace
        t.printStackTrace()
        view.showError(t.message ?: "error")
    }

    override fun onTimeline(statuses: Pageable<Status>, range: Range) {
        val timeline = statuses.part.map(::TimelineStatus)
        if (range.isNextPage()) {
            viewModel.statuses.addAll(timeline)
        } else {
            viewModel.statuses.addAll(0, timeline)
        }
        timeline.forEach {
            statusMap.put(it.entity.id, it)
        }
        statuses.link?.let {
            viewModel.mergeLink(it, range.isNextPage())
        }
        view.showTimeline(viewModel)
    }
}
