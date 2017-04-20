package com.sys1yagi.mastodon.android.ui.navigation.home

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(HomeFragmentObjectModule::class))
interface HomeFragmentComponent : AndroidInjector<HomeFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeFragment>() {

        abstract fun objectModule(objectModule: HomeFragmentObjectModule)

        override fun seedInstance(instance: HomeFragment) {
            objectModule(HomeFragmentObjectModule(instance))
        }
    }
}
