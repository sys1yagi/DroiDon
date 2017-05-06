package com.sys1yagi.mastodon.android.ui.navigation.home.notification

import com.sys1yagi.mastodon.android.extensions.async
import com.sys1yagi.mastodon.android.extensions.toJob
import com.sys1yagi.mastodon.android.extensions.ui
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.Notifications
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class NotificationInteractor
@Inject
constructor(
        val notifications: Notifications
)
    : NotificationContract.Interactor {

    var out: NotificationContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: NotificationContract.InteractorOutput) {
        this.out = out
    }

    override fun stopInteraction(out: NotificationContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }

    override fun getNotifications(range: Range) {
        async {
            val job = notifications.getNotifications().toJob()
            ui {
                try {
                    val n = job.await()
                    out?.onNotifications(n, range)
                } catch(e: Mastodon4jRequestException) {
                    out?.onError(e)
                }
            }
        }
    }
}
