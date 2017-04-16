package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(SetInstanceNameActivityComponent::class))
abstract class SetInstanceNameActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(SetInstanceNameActivity::class)
    abstract fun bindSetInstanceNameActivityInjectorFactory(builder: SetInstanceNameActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}
