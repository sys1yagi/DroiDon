package com.sys1yagi.mastodon.android.ui.navigation.home.notification

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(NotificationFragmentObjectModule::class))
interface NotificationFragmentComponent : AndroidInjector<NotificationFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<NotificationFragment>() {

        abstract fun objectModule(objectModule: NotificationFragmentObjectModule)

        override fun seedInstance(instance: NotificationFragment) {
            objectModule(NotificationFragmentObjectModule(instance))
        }
    }
}
