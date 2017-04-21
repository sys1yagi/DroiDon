package com.sys1yagi.mastodon.android.ui.entrypoint

import android.app.Activity
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
        fun checkRegistration(credential: Credential)
        fun registerCredential(credential: Credential)
        fun checkAuthentication(credential: Credential)
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onInstanceNameNotRegistered()
        fun onInstanceNameFound(credential: Credential)
        fun onRegistrationNotRegistered(credential: Credential)
        fun onRegistrationFound(credential: Credential)
        fun onUnAuthorizedOrExpired(credential: Credential)
        fun onAuthorized(credential: Credential)
    }

    interface Router {
        fun openSetInstanceNameActivity(activity: Activity)
        fun openLoginActivity(activity: Activity, instanceName: String)
        fun openHomeActivity(activity: Activity, instanceName: String)
    }
}
