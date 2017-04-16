package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SetInstanceNameInteractor
@Inject
constructor(
        // add dependencies
)
    : SetInstanceNameContract.Interactor {

    var out: SetInstanceNameContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: SetInstanceNameContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: SetInstanceNameContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }
}
