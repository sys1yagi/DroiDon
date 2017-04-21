package com.sys1yagi.mastodon.android.ui.auth.login

import android.content.Context
import com.sys1yagi.mastodon.android.data.database.Credential
import com.sys1yagi.mastodon4j.api.entity.auth.AccessToken

interface LoginContract {

    interface View {
        fun showLoginMessage(message: String)
        fun showError(message: String)
        fun finish()
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
        fun startOAuth()
        fun getAccessToken(code: String)
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stoplInteraction(out: InteractorOutput) // base
        fun getAccessToken(instanceName: String, code: String)
        fun saveAccessToken(instanceName: String, accessToken: String)
        fun getCredential(instanceName: String)
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onTargetCredential(credential: Credential)
        fun onCredentialNotFound(instanceName: String)
        fun onAccessToken(accessToken: AccessToken)
        fun onAccessTokenSaved()
    }

    interface Router {
        fun openOAuthPage(context: Context, credential: Credential)
        fun openHome(context: Context, instanceName: String)
    }
}
