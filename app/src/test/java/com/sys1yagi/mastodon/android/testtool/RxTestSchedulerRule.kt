package com.sys1yagi.mastodon.android.testtool

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.rules.ExternalResource

/**
 * :cool: https://github.com/DroidKaigi/conference-app-2017/blob/master/app/src/test/java/io/github/droidkaigi/confsched2017/util/RxTestSchedulerRule.kt
 */
class RxTestSchedulerRule : ExternalResource() {
    val testScheduler: TestScheduler = TestScheduler()

    override fun before() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setInitIoSchedulerHandler { testScheduler }
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }

        RxAndroidPlugins.reset()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { testScheduler }
        RxAndroidPlugins.setMainThreadSchedulerHandler { testScheduler }
    }

    override fun after() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    fun triggerActions(){
        testScheduler.triggerActions()
    }
}
