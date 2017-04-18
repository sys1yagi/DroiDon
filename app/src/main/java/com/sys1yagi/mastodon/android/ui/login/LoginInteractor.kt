package com.sys1yagi.mastodon.android.ui.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.gson.Gson
import com.sys1yagi.mastodon.android.data.database.Credential
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.method.Apps
import io.reactivex.disposables.Disposables
import okhttp3.OkHttpClient
import javax.inject.Inject

class LoginInteractor
@Inject
constructor(
        val okHttpClient: OkHttpClient,
        val gson: Gson
)
    : LoginContract.Interactor {

    var out: LoginContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: LoginContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: LoginContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }

    override fun login(context: Context, credential: Credential) {
        val apps = Apps(MastodonClient(credential.instanceName, okHttpClient, gson))
        val url = apps.getOAuthUrl(credential.clientId, scope = Scope(Scope.Name.ALL))
        val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (viewIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(viewIntent)
        } else {
            //todo
        }
    }
}
