package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(TootActivityComponent::class))
abstract class TootActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(TootActivity::class)
    abstract fun bindTootActivityInjectorFactory(builder: TootActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}
