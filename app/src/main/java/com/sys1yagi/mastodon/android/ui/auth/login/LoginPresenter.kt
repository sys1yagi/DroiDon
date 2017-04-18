package com.sys1yagi.mastodon.android.ui.auth.login

import android.app.Activity
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.data.database.Credential
import com.sys1yagi.mastodon.android.extensions.getRequired
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
        viewModel.instanceName = activity.intent.getRequired(LoginActivity.ARGS_INSTANCE_NAME)
        view.showLoginMessage(activity.getString(R.string.login_message, viewModel.instanceName))
    }

    override fun onPause() {
        interactor.stoplInteraction(this)
    }

    override fun startOAuth() {
        interactor.getCredential(viewModel.instanceName)
    }

    override fun onTargetCredential(credential: Credential) {
        viewModel.credential = credential
        router.openOAuthPage(activity, credential)
    }

    override fun onCredentialNotFound(instanceName: String) {
        // TODO
    }

    override fun saveAccessToken(accessToken: String) {
        interactor.saveAccessToken(viewModel.instanceName, accessToken)
    }

    override fun onAccessTokenSaved() {
        router.openHome(activity, viewModel.instanceName)
        view.finish()
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
