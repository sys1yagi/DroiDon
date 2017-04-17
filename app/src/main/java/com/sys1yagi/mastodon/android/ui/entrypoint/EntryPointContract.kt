package com.sys1yagi.mastodon.android.ui.entrypoint

import android.app.Activity
import android.content.Context
import com.sys1yagi.mastodon.android.data.database.Credential

interface EntryPointContract {

    interface View {
        fun showMessage(message: String)
        fun showError(message: String)
        fun finish()
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stoplInteraction(out: InteractorOutput) // base
        fun checkInstanceName()
        fun checkRegistration()
        fun login(context: Context, credential: Credential)

        fun registerCredential()
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onInstanceNameNotRegistered()
        fun onInstanceNameFound(credential: Credential)
        fun onRegistrationNotRegistered(credential: Credential)
        fun onRegistrationFound(credential: Credential)
        fun onAccessTokenNotFoundOrExpired()
        fun onAccessTokenFound(credential: Credential)
    }

    interface Router {
        fun openSetInstanceNameActivity(activity: Activity)
        fun openHomeActivity(activity: Activity)
    }
}
