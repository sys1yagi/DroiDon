package com.sys1yagi.mastodon.android.ui.auth.login

import android.app.Activity
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.data.database.Credential
import com.sys1yagi.mastodon4j.api.entity.auth.AccessToken
import javax.inject.Inject

class LoginPresenter
@Inject constructor(
        val activity: Activity,
        val instanceName: String,
        val view: LoginContract.View,
        val interactor: LoginContract.Interactor,
        val router: LoginContract.Router
) : LoginContract.Presenter, LoginContract.InteractorOutput {

    val viewModel = LoginViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
        view.showLoginMessage(activity.getString(R.string.login_message, instanceName))
    }

    override fun onPause() {
        interactor.stoplInteraction(this)
    }

    override fun startOAuth() {
        interactor.getCredential(instanceName)
    }

    override fun onTargetCredential(credential: Credential) {
        viewModel.credential = credential
        router.openOAuthPage(activity, credential)
    }

    override fun onCredentialNotFound(instanceName: String) {
        // TODO
    }

    override fun getAccessToken(code: String) {
        interactor.getAccessToken(instanceName, code)
    }

    override fun onAccessToken(accessToken: AccessToken) {
        interactor.saveAccessToken(instanceName, accessToken.accessToken)
    }

    override fun onAccessTokenSaved() {
        router.openHome(activity, instanceName)
        view.finish()
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
