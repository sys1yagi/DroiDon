package com.sys1yagi.mastodon.android.ui.navigation

import com.sys1yagi.mastodon.android.ui.navigation.home.HomeFragmentModule
import com.sys1yagi.mastodon.android.ui.navigation.home.federatedtimeline.FederatedTimelineFragmentModule
import com.sys1yagi.mastodon.android.ui.navigation.home.localtimeline.LocalTimelineFragmentModule
import com.sys1yagi.mastodon.android.ui.navigation.home.notification.NotificationFragmentModule
import com.sys1yagi.mastodon.android.ui.navigation.home.timeline.TimelineFragmentModule
import com.sys1yagi.mastodon.android.ui.navigation.settings.SettingsFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(
        NavigationActivityObjectModule::class,
        HomeFragmentModule::class,
        SettingsFragmentModule::class,
        TimelineFragmentModule::class,
        NotificationFragmentModule::class,
        LocalTimelineFragmentModule::class,
        FederatedTimelineFragmentModule::class
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
