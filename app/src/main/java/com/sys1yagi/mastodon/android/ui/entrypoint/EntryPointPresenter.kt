package com.sys1yagi.mastodon.android.ui.entrypoint

import android.app.Activity
import android.content.Context
import com.sys1yagi.mastodon.android.data.database.Credential
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
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
        view.showMessage("check instance name...")
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

    override fun onInstanceNameFound(credential: Credential) {
        view.showMessage("check ${credential.instanceName} credential...")
        interactor.checkRegistration()
    }

    override fun onRegistrationNotRegistered(credential: Credential) {
        view.showMessage("register ${credential.instanceName} credential...")
        interactor.registerCredential()
    }

    override fun onRegistrationFound(credential: Credential) {
        view.showMessage("check ${credential.instanceName} access token...")

        // TODO check token
        interactor.login(activity, credential)
    }

    override fun onAccessTokenNotFoundOrExpired() {
        // TODO
    }

    override fun onAccessTokenFound(credential: Credential) {
        router.openHomeActivity(activity)
    }
}
