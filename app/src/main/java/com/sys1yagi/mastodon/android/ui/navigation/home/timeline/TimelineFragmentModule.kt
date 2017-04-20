package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(TimelineFragmentComponent::class))
interface TimelineFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(TimelineFragment::class)
    abstract fun bindLocalTimelineInjectorFactory(builder: TimelineFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}
