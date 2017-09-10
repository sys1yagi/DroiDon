package com.sys1yagi.mastodon.android.ui.entrypoint

import com.sys1yagi.mastodon.android.data.database.Credential
import com.sys1yagi.mastodon.android.extensions.async
import com.sys1yagi.mastodon.android.extensions.ui
import kotlinx.coroutines.experimental.delay
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class EntryPointPresenter
@Inject constructor(
        val interactor: EntryPointContract.Interactor
) : EntryPointContract.Presenter, EntryPointContract.InteractorOutput {

    lateinit var view: EntryPointContract.View

    lateinit var router: EntryPointContract.Router

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

    override fun attachView(view: EntryPointContract.View) {
        this.view = view
    }

    override fun attachRouter(router: EntryPointContract.Router) {
        this.router = router
    }

    override fun onInstanceNameNotRegistered() {
        async {
            delay(1, TimeUnit.SECONDS)
            ui {
                router.openSetInstanceNameActivity()
                view.finish()
            }
        }
    }

    override fun onInstanceNameFound(credential: Credential) {
        view.showMessage("Check ${credential.instanceName} credential...")
        interactor.checkRegistration(credential)
    }

    override fun onRegistrationNotRegistered(credential: Credential) {
        view.showMessage("Register ${credential.instanceName} credential...")
        interactor.registerCredential(credential)
    }

    override fun onRegistrationFound(credential: Credential) {
        view.showMessage("Check ${credential.instanceName} authorization...")
        interactor.checkAuthentication(credential)
    }

    override fun onUnAuthorizedOrExpired(credential: Credential) {
        router.openLoginActivity(credential.instanceName)
        view.finish()
    }

    override fun onAuthorized(credential: Credential) {
        view.showMessage("The ${credential.instanceName} already authorized!")
        async {
            delay(1, TimeUnit.SECONDS)
            ui {
                router.openHomeActivity(credential.instanceName)
                view.finish()
            }
        }
    }
}
