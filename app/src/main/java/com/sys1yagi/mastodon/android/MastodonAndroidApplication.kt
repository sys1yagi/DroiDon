package com.sys1yagi.mastodon.android

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sys1yagi.mastodon.android.di.AppModule
import com.sys1yagi.mastodon.android.di.DaggerAppComponent
import com.sys1yagi.mastodon.android.di.OkHttpModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasDispatchingActivityInjector
import javax.inject.Inject
import timber.log.Timber.DebugTree
import timber.log.Timber

class MastodonAndroidApplication : MultiDexApplication(), HasDispatchingActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .okHttpModule(OkHttpModule())
                .build()
                .inject(this)

        Timber.plant(DebugTree())
        AndroidThreeTen.init(this)
        Fresco.initialize(this)

        if (BuildConfig.DEBUG) {
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build()
            )
        }
    }

    override fun activityInjector() = activityInjector
}
