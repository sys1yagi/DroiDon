package com.sys1yagi.mastodon.android.ui.auth.login

import com.sys1yagi.mastodon.android.data.database.AccessToken
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import com.sys1yagi.mastodon.android.extensions.async
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class LoginInteractor
@Inject
constructor(
        databaseProvider: OrmaDatabaseProvider
)
    : LoginContract.Interactor {

    val database = databaseProvider.database
    var out: LoginContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: LoginContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: LoginContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }

    override fun getCredential(instanceName: String) {
        database.selectFromCredential().instanceNameEq(instanceName).firstOrNull()?.let {
            out?.onTargetCredential(it)
        } ?: out?.onCredentialNotFound(instanceName)
    }

    override fun saveAccessToken(instanceName: String, accessToken: String) {
        disposable = async {
            database.transactionAsCompletable {
                database.insertIntoAccessToken(AccessToken().apply {
                    this.instanceName = instanceName
                    this.accessToken = accessToken
                })
                out?.onAccessTokenSaved()
            }.await()
        }
    }
}
