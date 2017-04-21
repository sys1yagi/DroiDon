package com.sys1yagi.mastodon.android.ui.navigation.home.toot

interface TootContract {

    interface View {
        fun showError(message: String)
        fun finish()
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
        fun toot(status: String)
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stopInteraction(out: InteractorOutput) // base
        fun toot(status: String)
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onSuccess()
    }

    interface Router {

    }
}
