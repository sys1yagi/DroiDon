package com.sys1yagi.mastodon.android.ui.navigation.home

import android.content.Context

interface HomeContract {

    interface View {
        fun showError(message: String)
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
        fun onFabClick()
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stopInteraction(out: InteractorOutput) // base
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
    }

    interface Router {
        fun openTootActivity(context: Context, instanceName: String)
    }
}
