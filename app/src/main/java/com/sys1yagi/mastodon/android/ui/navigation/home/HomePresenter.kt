package com.sys1yagi.mastodon.android.ui.navigation.home

import javax.inject.Inject
import android.support.v4.app.Fragment

class HomePresenter
@Inject constructor(
        val fragment: Fragment,
        val view: HomeContract.View,
        val interactor: HomeContract.Interactor,
        val router: HomeContract.Router
) : HomeContract.Presenter, HomeContract.InteractorOutput {

    val viewModel = HomeViewModel()

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
