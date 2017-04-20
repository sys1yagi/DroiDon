package com.sys1yagi.mastodon.android.ui.navigation

import android.view.MenuItem
import com.sys1yagi.mastodon.android.R

class NavigationPresenter(val view: NavigationContract.View, val interactor: NavigationContract.Interactor) : NavigationContract.Presenter, NavigationContract.InteractorOutput {

    val viewModel = NavigationViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
    }

    override fun onPause() {
        interactor.stoplInteraction(this)
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.navigation_home -> {
            view.showHome()
            true
        }
        R.id.navigation_trip -> {
            view.showTrip()
            true
        }
        R.id.navigation_settings -> {
            view.showSettings()
            true
        }
        else -> false
    }

    override fun onError(t: Throwable) {

    }
}
