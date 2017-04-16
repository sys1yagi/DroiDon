package com.sys1yagi.mastodon.android.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxTimelines
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideMastodonClient(client: OkHttpClient, gson: Gson): MastodonClient {
        return MastodonClient("mstdn.jp", client, gson)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient
            .Builder()
            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS))
            .build()

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
            .create()

    @Singleton
    @Provides
    fun provideRxTimeline(client: MastodonClient) = RxTimelines(client)
}
