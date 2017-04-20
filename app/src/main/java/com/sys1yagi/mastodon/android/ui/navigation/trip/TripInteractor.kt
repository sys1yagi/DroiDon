package com.sys1yagi.mastodon.android.ui.navigation.trip

import io.reactivex.disposables.Disposables
import javax.inject.Inject

class TripInteractor
@Inject
constructor(
        // add dependencies
)
    : TripContract.Interactor {

    var out: TripContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: TripContract.InteractorOutput) {
        this.out = out
    }

    override fun stopInteraction(out: TripContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }
}
