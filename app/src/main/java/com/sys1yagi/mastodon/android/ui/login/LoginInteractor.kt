package com.sys1yagi.mastodon.android.ui.login

import co.metalab.asyncawait.async
import com.sys1yagi.mastodon.android.data.database.AccessToken
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import com.sys1yagi.mastodon.android.extensions.await
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

    override fun saveAccessToken(instanceName:String, accessToken: String) {
        async{
            await(database.transactionAsCompletable {
                database.insertIntoAccessToken(AccessToken().apply {
                    this.instanceName = instanceName
                    this.accessToken = accessToken
                })
                out?.onAccessTokenSaved()
            })
        }
    }
}
