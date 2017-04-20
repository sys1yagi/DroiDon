package com.sys1yagi.mastodon.android.ui.navigation.trip

import javax.inject.Inject
import android.support.v4.app.Fragment

class TripPresenter
@Inject constructor(
        val fragment: Fragment,
        val view: TripContract.View,
        val interactor: TripContract.Interactor,
        val router: TripContract.Router
) : TripContract.Presenter, TripContract.InteractorOutput {

    val viewModel = TripViewModel()

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
