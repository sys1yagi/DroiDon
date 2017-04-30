package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon.android.di.FragmentScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@FragmentScope
@Subcomponent(modules = arrayOf(TimelineFragmentObjectModule::class))
interface TimelineFragmentComponent : AndroidInjector<TimelineFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TimelineFragment>() {

        abstract fun objectModule(objectModule: TimelineFragmentObjectModule)

        override fun seedInstance(instance: TimelineFragment) {
            objectModule(TimelineFragmentObjectModule(instance, instance.type))
        }
    }
}
