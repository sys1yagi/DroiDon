package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import javax.inject.Inject
import android.app.Activity

class TootPresenter
@Inject constructor(
        val activity: Activity,
        val view: TootContract.View,
        val interactor: TootContract.Interactor,
        val router: TootContract.Router
) : TootContract.Presenter, TootContract.InteractorOutput {

    val viewModel = TootViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
    }

    override fun onPause() {
        interactor.stopInteraction(this)
    }

    override fun toot(status: String) {
        interactor.toot(status)
    }

    override fun onSuccess() {
        view.finish()
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
