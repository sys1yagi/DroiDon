package com.sys1yagi.mastodon.android

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.facebook.drawee.backends.pipeline.Fresco
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sys1yagi.mastodon.android.di.*
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject


class DroiDonApplication : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        DaggerAppComponent
                .builder()
                .create(this)
                .inject(this)

        Timber.plant(DebugTree())
        AndroidThreeTen.init(this)
        Fresco.initialize(this)
    }

    override fun activityInjector() = activityInjector
}
