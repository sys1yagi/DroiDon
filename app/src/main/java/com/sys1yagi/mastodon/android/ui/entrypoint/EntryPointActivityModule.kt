package com.sys1yagi.mastodon.android.ui.entrypoint

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(EntryPointActivityComponent::class))
abstract class EntryPointActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(EntryPointActivity::class)
    abstract fun bindEntryPointActivityInjectorFactory(builder: EntryPointActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}
