package com.sys1yagi.mastodon.android.ui.home.instance.localtimeline

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(LocalTimelineFragmentObjectModule::class))
interface LocalTimelineFragmentComponent : AndroidInjector<LocalTimelineFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<LocalTimelineFragment>() {

        abstract fun objectModule(objectModule: LocalTimelineFragmentObjectModule)

        override fun seedInstance(instance: LocalTimelineFragment) {
            objectModule(LocalTimelineFragmentObjectModule(instance))
        }
    }
}
