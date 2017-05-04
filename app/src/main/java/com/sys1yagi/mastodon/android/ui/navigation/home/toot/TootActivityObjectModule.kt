package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import android.app.Activity
import com.google.gson.Gson
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxMedia
import com.sys1yagi.mastodon4j.rx.RxStatuses
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class TootActivityObjectModule(val instanceName: String, val view: TootContract.View) {
    @Provides
    fun providePresenter(interactor: TootInteractor, router: TootRouter): TootContract.Presenter {
        return TootPresenter(view as Activity, view, interactor, router)
    }

    @Provides
    fun provideMastodonClient(client: OkHttpClient.Builder, gson: Gson, databaseProvider: OrmaDatabaseProvider): MastodonClient {
        val accessToken = databaseProvider
                .database
                .selectFromAccessToken()
                .instanceNameEq(instanceName)
                .firstOrNull()?.accessToken

        return MastodonClient.Builder(instanceName, client, gson)
                .apply {
                    accessToken?.let {
                        accessToken(it)
                    }
                }
                .build()
    }

    @Provides
    fun provideRxStatues(client: MastodonClient) = RxStatuses(client)

    @Provides
    fun provideRxMedia(client: MastodonClient) = RxMedia(client)
}
