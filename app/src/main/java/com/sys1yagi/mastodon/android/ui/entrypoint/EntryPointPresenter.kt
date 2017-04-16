package com.sys1yagi.mastodon.android.ui.entrypoint

import android.app.Activity
import android.content.Context
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class EntryPointPresenter
@Inject constructor(
        val activity: Activity,
        val view: EntryPointContract.View,
        val interactor: EntryPointContract.Interactor,
        val router: EntryPointContract.Router
) : EntryPointContract.Presenter, EntryPointContract.InteractorOutput {

    val viewModel = EntryPointViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
        interactor.checkInstanceName()
    }

    override fun onPause() {
        interactor.stoplInteraction(this)
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }

    override fun onInstanceNameNotRegistered() {
        Completable
                .complete()
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    router.openSetInstanceNameActivity(activity)
                    view.finish()
                }
    }

    override fun onInstanceNameFound() {
        interactor.checkRegistration()
    }

    override fun onRegistrationNotRegistered() {
        // TODO
    }

    override fun onRegistrationFound() {
        interactor.checkAccessToken()
    }

    override fun onAccessTokenNotFoundOrExpired() {
        // TODO
    }

    override fun onAccessTokenFound() {
        router.openHomeActivity(activity)
    }
}
