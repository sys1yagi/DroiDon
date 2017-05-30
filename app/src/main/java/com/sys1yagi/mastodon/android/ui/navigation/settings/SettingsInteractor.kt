package com.sys1yagi.mastodon.android.ui.navigation.settings

import io.reactivex.disposables.Disposables
import javax.inject.Inject

class SettingsInteractor
@Inject
constructor(
        // add dependencies
)
    : SettingsContract.Interactor {

    var out: SettingsContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: SettingsContract.InteractorOutput) {
        this.out = out
    }

    override fun stopInteraction(out: SettingsContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }

    override fun logout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
