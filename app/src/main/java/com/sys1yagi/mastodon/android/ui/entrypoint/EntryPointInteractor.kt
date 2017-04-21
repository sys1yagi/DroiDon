package com.sys1yagi.mastodon.android.ui.entrypoint

import android.content.ContentValues
import com.google.gson.Gson
import com.sys1yagi.mastodon.android.data.database.Credential
import com.sys1yagi.mastodon.android.data.database.OrmaDatabase
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import com.sys1yagi.mastodon.android.extensions.async
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.entity.auth.AppRegistration
import com.sys1yagi.mastodon4j.rx.RxApps
import io.reactivex.disposables.Disposables
import okhttp3.OkHttpClient
import javax.inject.Inject

class EntryPointInteractor
@Inject
constructor(
        val okHttpClient: OkHttpClient,
        val gson: Gson,
        databaseProvider: OrmaDatabaseProvider
)
    : EntryPointContract.Interactor {

    val database: OrmaDatabase = databaseProvider.database
    var out: EntryPointContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: EntryPointContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: EntryPointContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }

    override fun checkInstanceName() {
        database.selectFromCredential().firstOrNull()?.let {
            out?.onInstanceNameFound(it)
        } ?: out?.onInstanceNameNotRegistered()
    }

    override fun checkRegistration(credential: Credential) {
        database.selectFromCredential().firstOrNull()?.let {
            if (it.clientId.isEmpty() && it.clientSecret.isEmpty()) {
                out?.onRegistrationNotRegistered(it)
            } else {
                out?.onRegistrationFound(it)
            }
        } ?: out?.onInstanceNameNotRegistered()
    }

    override fun registerCredential(credential: Credential) {
        val client = MastodonClient(credential.instanceName, okHttpClient, gson)
        val apps = RxApps(client)

        disposable = async {
            try {
                val appRegistration = apps.createApp(clientName = "mastodon-android-sys1yagi", scope = Scope(Scope.Name.ALL)).await()
                saveCredential(credential, appRegistration).await()
                out?.onRegistrationFound(credential)
            } catch(e: Throwable) {
                out?.onError(e)
            }
        }
    }

    fun saveCredential(credential: Credential, appRegistration: AppRegistration) = run {
        database.updateCredential().idEq(credential.id)
                .putAll(ContentValues().apply {
                    put("registration_id", appRegistration.id)
                    put("client_id", appRegistration.clientId)
                    put("client_secret", appRegistration.clientSecret)
                })
                .executeAsSingle()
    }

    override fun checkAuthentication(credential: Credential) {
        database.selectFromAccessToken().instanceNameEq(credential.instanceName).firstOrNull()?.let{
            out?.onAuthorized(credential)
        } ?: out?.onUnAuthorizedOrExpired(credential)
    }
}
