package com.sys1yagi.mastodon.android.ui.login

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginInteractor
@Inject
constructor(
        // add dependencies
)
    : LoginContract.Interactor {

    var out: LoginContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: LoginContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: LoginContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }
}
