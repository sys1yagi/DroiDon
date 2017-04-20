package com.sys1yagi.mastodon.android.ui.navigation

import android.view.MenuItem

interface NavigationContract {
    interface View {
        fun showHome()
        fun showTrip()
        fun showSettings()
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
        fun onNavigationItemSelected(item: MenuItem): Boolean
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stoplInteraction(out: InteractorOutput) // base
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
    }

    interface Router {
        // TODO
    }
}
