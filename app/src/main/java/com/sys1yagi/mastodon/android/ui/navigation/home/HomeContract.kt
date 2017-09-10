package com.sys1yagi.mastodon.android.ui.navigation.home

import android.support.v4.app.Fragment

interface HomeContract {

    companion object {
        const val REQUEST_CODE_TOOT = 0x2516
    }

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
        fun openTootActivity(fragment: Fragment, instanceName: String)
    }
}
