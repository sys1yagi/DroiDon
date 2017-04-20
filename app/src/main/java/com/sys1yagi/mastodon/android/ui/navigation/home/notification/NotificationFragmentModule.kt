package com.sys1yagi.mastodon.android.ui.navigation.home.notification

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(NotificationFragmentComponent::class))
interface NotificationFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(NotificationFragment::class)
    abstract fun bindLocalNotificationInjectorFactory(builder: NotificationFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}
