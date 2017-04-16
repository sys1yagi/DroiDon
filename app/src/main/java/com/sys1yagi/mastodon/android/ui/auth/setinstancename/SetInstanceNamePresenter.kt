package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import javax.inject.Inject

class SetInstanceNamePresenter
@Inject constructor(
        val view: SetInstanceNameContract.View,
        val interactor: SetInstanceNameContract.Interactor
) : SetInstanceNameContract.Presenter, SetInstanceNameContract.InteractorOutput {

    val viewModel = SetInstanceNameViewModel()

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
