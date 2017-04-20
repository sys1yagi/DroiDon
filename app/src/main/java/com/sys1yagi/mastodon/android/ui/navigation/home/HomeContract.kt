package com.sys1yagi.mastodon.android.ui.navigation.home

interface HomeContract {

    interface View {
        fun showError(message: String)
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stopInteraction(out: InteractorOutput) // base
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
    }

    interface Router {

    }
}
