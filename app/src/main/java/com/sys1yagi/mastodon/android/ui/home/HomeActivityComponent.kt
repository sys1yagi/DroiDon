package com.sys1yagi.mastodon.android.ui.home

import com.sys1yagi.mastodon.android.ui.home.localtimeline.LocalTimelineFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(
        HomeActivityObjectModule::class,
        LocalTimelineFragmentModule::class
))
interface HomeActivityComponent : AndroidInjector<HomeActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeActivity>() {

        abstract fun objectModule(objectModule: HomeActivityObjectModule)

        override fun seedInstance(instance: HomeActivity) {
            objectModule(HomeActivityObjectModule(instance))
        }
    }
}
