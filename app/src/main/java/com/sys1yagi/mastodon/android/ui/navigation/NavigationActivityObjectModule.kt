package com.sys1yagi.mastodon.android.ui.navigation

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxTimelines
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class NavigationActivityObjectModule(val view: NavigationContract.View) {
    @Provides
    fun providePresenter(interactor: NavigationInteractor): NavigationContract.Presenter {
        return NavigationPresenter(view, interactor)
    }

    @Provides
    fun provideMastodonClient(client: OkHttpClient, gson: Gson): MastodonClient {
        return MastodonClient("mstdn.jp", client, gson)
    }
}
