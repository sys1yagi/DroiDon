package com.sys1yagi.mastodon.android.di

import android.content.Context
import com.facebook.stetho.Stetho
import dagger.Module
import javax.inject.Singleton

@Singleton
@Module
class StethoModule(context: Context) {
    init {
        Stetho.initialize(Stetho.newInitializerBuilder(context)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                .build()
        )
    }
}
