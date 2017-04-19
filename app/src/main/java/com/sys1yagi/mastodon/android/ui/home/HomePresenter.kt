package com.sys1yagi.mastodon.android.ui.home

import android.view.MenuItem
import com.sys1yagi.mastodon.android.R

class HomePresenter(val view: HomeContract.View, val interactor: HomeContract.Interactor) : HomeContract.Presenter, HomeContract.InteractorOutput {

    val viewModel = HomeViewModel()

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
