package com.sys1yagi.mastodon.android.ui.navigation

import com.google.gson.Gson
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import com.sys1yagi.mastodon.android.di.ActivityScope
import com.sys1yagi.mastodon.android.util.TabLayoutEventSubject
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxTimelines
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@ActivityScope
@Module
class NavigationActivityObjectModule(val instanceName: String, val view: NavigationContract.View) {
    @Provides
    fun providePresenter(interactor: NavigationInteractor): NavigationContract.Presenter {
        return NavigationPresenter(view, interactor)
    }

    @Provides
    fun provideMastodonClient(client: OkHttpClient, gson: Gson, databaseProvider: OrmaDatabaseProvider): MastodonClient {
        val accessToken = databaseProvider
                .database
                .selectFromAccessToken()
                .instanceNameEq(instanceName)
                .firstOrNull()?.accessToken

        return MastodonClient(instanceName, client, gson, accessToken)
    }

    @ActivityScope
    @Provides
    fun provideTabLayoutEventSubject(): TabLayoutEventSubject {
        return TabLayoutEventSubject()
    }
}
