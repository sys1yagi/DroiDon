package com.sys1yagi.mastodon.android.ui.navigation.home.notification

import javax.inject.Inject
import android.support.v4.app.Fragment

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

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
