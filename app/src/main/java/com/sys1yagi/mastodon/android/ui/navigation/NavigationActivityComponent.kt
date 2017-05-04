package com.sys1yagi.mastodon.android.ui.navigation

import com.sys1yagi.mastodon.android.di.ActivityScope
import com.sys1yagi.mastodon.android.ui.navigation.home.HomeFragmentModule
import com.sys1yagi.mastodon.android.ui.navigation.home.notification.NotificationFragmentModule
import com.sys1yagi.mastodon.android.ui.navigation.home.timeline.TimelineFragmentModule
import com.sys1yagi.mastodon.android.ui.navigation.settings.SettingsFragmentModule
import com.sys1yagi.mastodon.android.ui.navigation.trip.TripFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(modules = arrayOf(
        NavigationActivityObjectModule::class,
        HomeFragmentModule::class,
        TripFragmentModule::class,
        SettingsFragmentModule::class,
        TimelineFragmentModule::class,
        NotificationFragmentModule::class
))
interface NavigationActivityComponent : AndroidInjector<NavigationActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<NavigationActivity>() {

        abstract fun objectModule(objectModule: NavigationActivityObjectModule)

        override fun seedInstance(instance: NavigationActivity) {
            objectModule(NavigationActivityObjectModule(instance.primaryInstanceName, instance))
        }
    }
}
