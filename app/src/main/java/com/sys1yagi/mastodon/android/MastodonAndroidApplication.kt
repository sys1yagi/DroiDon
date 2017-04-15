package com.sys1yagi.mastodon.android

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sys1yagi.mastodon.android.di.DaggerAppComponent
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
                .build()
                .inject(this)
        Timber.plant(DebugTree())
        AndroidThreeTen.init(this)
    }

    override fun activityInjector() = activityInjector
}
