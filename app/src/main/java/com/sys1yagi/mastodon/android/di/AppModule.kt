package com.sys1yagi.mastodon.android.di

import android.app.Application
import android.content.Context
import com.github.gfx.android.orma.AccessThreadConstraint
import com.google.gson.GsonBuilder
import com.sys1yagi.mastodon.android.data.database.OrmaDatabase
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class AppModule(val application: Application) {

    @Singleton
    @Provides
    fun provideApplication() = application

    @Singleton
    @Provides
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
            .create()

    @Singleton
    @Provides
    fun provideOrmaDatabaseProvider(context: Context) = OrmaDatabaseProvider(OrmaDatabase
            .builder(context)
            .writeOnMainThread(AccessThreadConstraint.FATAL)
            .readOnMainThread(AccessThreadConstraint.WARNING)
            .build())
}
