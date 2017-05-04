package com.sys1yagi.mastodon.android.ui.auth.login

import android.app.Activity
import com.google.gson.Gson
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxApps
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class LoginActivityObjectModule(val instanceName: String, val view: LoginContract.View) {
    @Provides
    fun providePresenter(interactor: LoginInteractor, router: LoginRouter): LoginContract.Presenter {
        return LoginPresenter(view as Activity, instanceName, view, interactor, router)
    }

    @Provides
    fun provideMastodonClient(builder: OkHttpClient.Builder, gson: Gson): MastodonClient {
        return MastodonClient.Builder(instanceName, builder, gson).build()
    }

    @Provides
    fun providesRxApps(client: MastodonClient): RxApps {
        return RxApps(client)
    }
}
