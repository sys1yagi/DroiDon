package com.sys1yagi.mastodon.android.ui.auth.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.gson.Gson
import com.sys1yagi.mastodon.android.data.database.Credential
import com.sys1yagi.mastodon.android.ui.navigation.NavigationActivity
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.method.Apps
import okhttp3.OkHttpClient
import javax.inject.Inject

class LoginRouter
@Inject
constructor(
        val okHttpClientBuilder: OkHttpClient.Builder,
        val gson: Gson
)
    : LoginContract.Router {
    override fun openOAuthPage(context: Context, credential: Credential) {
        val apps = Apps(MastodonClient.Builder(credential.instanceName, okHttpClientBuilder, gson).build())
        val url = apps.getOAuthUrl(credential.clientId, scope = Scope(Scope.Name.ALL))
        val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (viewIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(viewIntent)
        } else {
            // TODO
        }
    }

    override fun openHome(context: Context, instanceName: String) {
        context.startActivity(NavigationActivity.createIntent(context, instanceName))
    }
}
