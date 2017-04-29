package com.sys1yagi.mastodon.android

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.facebook.drawee.backends.pipeline.Fresco
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sys1yagi.mastodon.android.di.*
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasDispatchingActivityInjector
import io.fabric.sdk.android.Fabric
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject


class MastodonAndroidApplication : MultiDexApplication(), HasDispatchingActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent: AppComponent;

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .okHttpModule(OkHttpModule())
                .stethoModule(StethoModule(this))
                .build()
        appComponent.inject(this)

        Timber.plant(DebugTree())
        AndroidThreeTen.init(this)
        Fresco.initialize(this)
    }

    override fun activityInjector() = activityInjector
}
