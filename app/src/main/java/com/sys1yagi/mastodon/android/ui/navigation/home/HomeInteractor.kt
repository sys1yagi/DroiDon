package com.sys1yagi.mastodon.android.ui.navigation.home

import io.reactivex.disposables.Disposables
import javax.inject.Inject

class HomeInteractor
@Inject
constructor(
        // add dependencies
)
    : HomeContract.Interactor {

    var out: HomeContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: HomeContract.InteractorOutput) {
        this.out = out
    }

    override fun stopInteraction(out: HomeContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }
}
