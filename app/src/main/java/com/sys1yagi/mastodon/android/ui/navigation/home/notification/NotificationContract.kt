package com.sys1yagi.mastodon.android.ui.navigation.home.notification

import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Notification

interface NotificationContract {

    interface View {
        fun showNotifications(viewModel: NotificationViewModel)
        fun showProgress()
        fun showError(message: String)
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stopInteraction(out: InteractorOutput) // base

        fun getNotifications(range: Range)
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onNotifications(notifications: Pageable<Notification>, range: Range)
    }

    interface Router {

    }
}
