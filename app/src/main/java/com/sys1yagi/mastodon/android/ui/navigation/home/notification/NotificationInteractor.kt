package com.sys1yagi.mastodon.android.ui.navigation.home.notification

import io.reactivex.disposables.Disposables
import javax.inject.Inject

class NotificationInteractor
@Inject
constructor(
        // add dependencies
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
}
