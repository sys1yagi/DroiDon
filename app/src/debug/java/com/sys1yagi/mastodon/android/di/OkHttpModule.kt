package com.sys1yagi.mastodon.android.di

import com.facebook.stetho.okhttp3.StethoInterceptor
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
            .addNetworkInterceptor(StethoInterceptor())
}
