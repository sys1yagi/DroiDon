package com.sys1yagi.mastodon.android.di

import dagger.Module
import dagger.Provides
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Module
class OkHttpModule {
    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient
            .Builder()
            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS))
            .build()
}
