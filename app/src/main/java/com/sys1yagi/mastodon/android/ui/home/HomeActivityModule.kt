package com.sys1yagi.mastodon.android.ui.home

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(HomeActivityComponent::class))
abstract class HomeActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: HomeActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}
