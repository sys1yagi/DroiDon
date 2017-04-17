package com.sys1yagi.mastodon.android.ui.main

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxTimelines
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class MainActivityObjectModule(val view: MainContract.View) {
    @Provides
    fun providePresenter(interactor: MainInteractor): MainContract.Presenter {
        return MainPresenter(view, interactor)
    }

    @Provides
    fun provideMastodonClient(client: OkHttpClient, gson: Gson): MastodonClient {
        return MastodonClient("mstdn.jp", client, gson)
    }

    @Provides
    fun provideRxTimeline(client: MastodonClient) = RxTimelines(client)
}
