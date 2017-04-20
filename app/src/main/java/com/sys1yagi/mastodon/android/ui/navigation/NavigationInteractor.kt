package com.sys1yagi.mastodon.android.ui.navigation

import io.reactivex.disposables.Disposables
import javax.inject.Inject

class NavigationInteractor
@Inject
constructor()
    : NavigationContract.Interactor {

    var out: NavigationContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: NavigationContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: NavigationContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }
}
