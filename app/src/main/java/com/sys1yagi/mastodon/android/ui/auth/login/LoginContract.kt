package com.sys1yagi.mastodon.android.ui.auth.login

import android.content.Context
import com.sys1yagi.mastodon.android.data.database.Credential

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
        fun saveAccessToken(accessToken: String)
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stoplInteraction(out: InteractorOutput) // base
        fun saveAccessToken(instanceName:String, accessToken: String)
        fun getCredential(instanceName: String)
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onTargetCredential(credential: Credential)
        fun onCredentialNotFound(instanceName: String)
        fun onAccessTokenSaved()
    }

    interface Router {
        fun openOAuthPage(context: Context, credential: Credential)
        fun openHome(context: Context, instanceName: String)
    }
}
