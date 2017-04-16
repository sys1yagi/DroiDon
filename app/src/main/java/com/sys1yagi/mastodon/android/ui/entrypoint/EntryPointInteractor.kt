package com.sys1yagi.mastodon.android.ui.entrypoint

import io.reactivex.disposables.Disposables
import javax.inject.Inject

class EntryPointInteractor
@Inject
constructor(
        // add dependencies
)
    : EntryPointContract.Interactor {

    var out: EntryPointContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: EntryPointContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: EntryPointContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }

    override fun checkInstanceName() {
        out?.onInstanceNameNotRegistered()
    }

    override fun checkRegistration() {

    }

    override fun checkAccessToken() {

    }
}
