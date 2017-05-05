package com.sys1yagi.mastodon.android.ui.auth.login

import com.sys1yagi.mastodon.android.data.database.AccessToken
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import com.sys1yagi.mastodon.android.extensions.async
import com.sys1yagi.mastodon.android.extensions.toJob
import com.sys1yagi.mastodon.android.extensions.ui
import com.sys1yagi.mastodon4j.api.method.Apps
import kotlinx.coroutines.experimental.Job
import javax.inject.Inject

class LoginInteractor
@Inject
constructor(
        databaseProvider: OrmaDatabaseProvider,
        val apps: Apps
)
    : LoginContract.Interactor {

    val database = databaseProvider.database
    var out: LoginContract.InteractorOutput? = null
    var disposable: Job? = null

    override fun startInteraction(out: LoginContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: LoginContract.InteractorOutput) {
        disposable?.cancel()
        this.out = null
    }

    override fun getCredential(instanceName: String) {
        database.selectFromCredential().instanceNameEq(instanceName).firstOrNull()?.let {
            out?.onTargetCredential(it)
        } ?: out?.onCredentialNotFound(instanceName)
    }

    override fun getAccessToken(instanceName: String, code: String) {
        database.selectFromCredential().instanceNameEq(instanceName).firstOrNull()?.let {
            async {
                val accessToken = apps.getAccessToken(
                        it.clientId,
                        it.clientSecret,
                        code = code
                ).toJob()
                ui {
                    try {
                        out?.onAccessToken(accessToken.await())
                    } catch(e: Throwable) {
                        out?.onError(e)
                    }
                }
            }
        } ?: out?.onCredentialNotFound(instanceName)
    }

    override fun saveAccessToken(instanceName: String, accessToken: String) {
        disposable = async {
            database.transactionSync {
                database.insertIntoAccessToken(AccessToken().apply {
                    this.instanceName = instanceName
                    this.accessToken = accessToken
                })
                ui {
                    out?.onAccessTokenSaved()
                }
            }
        }
    }
}
