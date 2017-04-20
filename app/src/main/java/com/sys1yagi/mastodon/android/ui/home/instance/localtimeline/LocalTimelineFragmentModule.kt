package com.sys1yagi.mastodon.android.ui.home.instance.localtimeline

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(LocalTimelineFragmentComponent::class))
interface LocalTimelineFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(LocalTimelineFragment::class)
    abstract fun bindLocalTimelineFragmentInjectorFactory(builder: LocalTimelineFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}
