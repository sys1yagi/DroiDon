package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import android.app.Activity
import javax.inject.Inject

class SetInstanceNamePresenter
@Inject constructor(
        val activity: Activity,
        val view: SetInstanceNameContract.View,
        val interactor: SetInstanceNameContract.Interactor,
        val router: SetInstanceNameContract.Router
) : SetInstanceNameContract.Presenter, SetInstanceNameContract.InteractorOutput {

    val viewModel = SetInstanceNameViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
    }

    override fun onPause() {
        interactor.stoplInteraction(this)
    }

    override fun saveInstanceName(instanceName: String) {
        interactor.saveInstanceName(instanceName)
    }

    override fun onInstanceNameSaved() {
        view.finish()
        router.openEntryPointActivity(activity)
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
