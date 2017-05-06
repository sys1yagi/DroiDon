package com.sys1yagi.mastodon.android.ui.navigation.home.notification

import javax.inject.Inject
import android.support.v4.app.Fragment
import com.sys1yagi.mastodon.android.extensions.isNextPage
import com.sys1yagi.mastodon.android.extensions.merge
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Notification

class NotificationPresenter
@Inject constructor(
        val fragment: Fragment,
        val view: NotificationContract.View,
        val interactor: NotificationContract.Interactor,
        val router: NotificationContract.Router
) : NotificationContract.Presenter, NotificationContract.InteractorOutput {

    val viewModel = NotificationViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
    }

    override fun onPause() {
        interactor.stopInteraction(this)
    }

    override fun onNotifications(notifications: Pageable<Notification>, range: Range) {
        val notification = notifications.part.map { com.sys1yagi.mastodon.android.data.model.Notification(it) }
        if (range.isNextPage()) {
            viewModel.notifications.addAll(notification)
        } else {
            viewModel.notifications.addAll(0, notification)
        }
        notifications.link?.let {
            viewModel.link = viewModel.link.merge(it, range.isNextPage())
        }

        view.showNotifications(viewModel)
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
