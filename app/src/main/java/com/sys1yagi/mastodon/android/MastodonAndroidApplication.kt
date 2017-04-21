package com.sys1yagi.mastodon.android

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sys1yagi.mastodon.android.di.AppModule
import com.sys1yagi.mastodon.android.di.DaggerAppComponent
import com.sys1yagi.mastodon.android.di.OkHttpModule
import com.sys1yagi.mastodon.android.di.StethoModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasDispatchingActivityInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class MastodonAndroidApplication : MultiDexApplication(), HasDispatchingActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .okHttpModule(OkHttpModule())
                .stethoModule(StethoModule(this))
                .build()
                .inject(this)

        Timber.plant(DebugTree())
        AndroidThreeTen.init(this)
        Fresco.initialize(this)
    }

    override fun activityInjector() = activityInjector
}
