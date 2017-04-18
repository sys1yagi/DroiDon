package com.sys1yagi.mastodon.android.ui.home

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxTimelines
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class HomeActivityObjectModule(val view: HomeContract.View) {
    @Provides
    fun providePresenter(interactor: HomeInteractor): HomeContract.Presenter {
        return HomePresenter(view, interactor)
    }

    @Provides
    fun provideMastodonClient(client: OkHttpClient, gson: Gson): MastodonClient {
        return MastodonClient("mstdn.jp", client, gson)
    }

    @Provides
    fun provideRxTimeline(client: MastodonClient) = RxTimelines(client)
}
