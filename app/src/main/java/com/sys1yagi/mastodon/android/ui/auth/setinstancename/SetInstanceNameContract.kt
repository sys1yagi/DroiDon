package com.sys1yagi.mastodon.android.ui.auth.setinstancename

interface SetInstanceNameContract {

    interface View {
        fun showError(message: String)
        fun finish()
    }

    interface Presenter {
        fun attachView(view: SetInstanceNameContract.View)
        fun attachRouter(router: SetInstanceNameContract.Router)
        fun onResume() // base
        fun onPause()  // base
        fun saveInstanceName(instanceName: String)
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stoplInteraction(out: InteractorOutput) // base
        fun saveInstanceName(instanceName: String)
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onInstanceNameSaved()
    }

    interface Router {
        fun openEntryPointActivity()
    }
}
