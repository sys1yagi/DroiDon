package com.sys1yagi.mastodon.android.ui.navigation

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(NavigationActivityComponent::class))
abstract class NavigationActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(NavigationActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: NavigationActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}
