package com.sys1yagi.mastodon.android.ui.home

import io.reactivex.disposables.Disposables
import javax.inject.Inject

class HomeInteractor
@Inject
constructor()
    : HomeContract.Interactor {

    var out: HomeContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: HomeContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: HomeContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }
}
