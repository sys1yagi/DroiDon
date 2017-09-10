package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import javax.inject.Inject

class SetInstanceNamePresenter
@Inject constructor(
        val interactor: SetInstanceNameContract.Interactor
) : SetInstanceNameContract.Presenter, SetInstanceNameContract.InteractorOutput {

    lateinit var view: SetInstanceNameContract.View

    lateinit var router: SetInstanceNameContract.Router

    val viewModel = SetInstanceNameViewModel()


    override fun attachView(view: SetInstanceNameContract.View) {
        this.view = view
    }

    override fun attachRouter(router: SetInstanceNameContract.Router) {
        this.router = router
    }

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
        router.openEntryPointActivity()
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
