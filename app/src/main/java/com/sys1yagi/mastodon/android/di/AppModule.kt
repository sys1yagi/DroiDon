package com.sys1yagi.mastodon.android.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sys1yagi.mastodon4j.MastodonClient
import dagger.Module
import dagger.Provides
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
            .build()

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
            .create()
}
