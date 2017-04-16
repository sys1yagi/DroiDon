package com.sys1yagi.mastodon.android.ui.entrypoint

import android.app.Activity

interface EntryPointContract {

    interface View {
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
        fun checkAccessToken()
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onInstanceNameNotRegistered()
        fun onInstanceNameFound()
        fun onRegistrationNotRegistered()
        fun onRegistrationFound()
        fun onAccessTokenNotFoundOrExpired()
        fun onAccessTokenFound()
    }

    interface Router {
        fun openSetInstanceNameActivity(activity: Activity)
        fun openHomeActivity(activity: Activity)
    }
}
