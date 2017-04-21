package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import com.sys1yagi.mastodon.android.extensions.async
import com.sys1yagi.mastodon4j.rx.RxStatuses
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class TootInteractor
@Inject
constructor(
        val statuses: RxStatuses
)
    : TootContract.Interactor {

    var out: TootContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: TootContract.InteractorOutput) {
        this.out = out
    }

    override fun stopInteraction(out: TootContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }

    override fun toot(status: String) {
        disposable = async {
            statuses.postStatus(status).await()
            out?.onSuccess()
        }
    }
}
