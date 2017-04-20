package com.sys1yagi.mastodon.android.ui.navigation

import com.sys1yagi.mastodon.android.ui.navigation.home.HomeFragmentModule
import com.sys1yagi.mastodon.android.ui.navigation.home.localtimeline.LocalTimelineFragmentModule
import com.sys1yagi.mastodon.android.ui.navigation.home.timeline.TimelineFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(
        NavigationActivityObjectModule::class,
        HomeFragmentModule::class,
        TimelineFragmentModule::class,
        LocalTimelineFragmentModule::class
))
interface NavigationActivityComponent : AndroidInjector<NavigationActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<NavigationActivity>() {

        abstract fun objectModule(objectModule: NavigationActivityObjectModule)

        override fun seedInstance(instance: NavigationActivity) {
            objectModule(NavigationActivityObjectModule(instance))
        }
    }
}
