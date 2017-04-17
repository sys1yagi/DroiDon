package com.sys1yagi.mastodon.android.ui.login

import android.app.Activity
import javax.inject.Inject

class LoginPresenter
@Inject constructor(
        val activity: Activity,
        val view: LoginContract.View,
        val interactor: LoginContract.Interactor,
        val router: LoginContract.Router
) : LoginContract.Presenter, LoginContract.InteractorOutput {

    val viewModel = LoginViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
    }

    override fun onPause() {
        interactor.stoplInteraction(this)
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
