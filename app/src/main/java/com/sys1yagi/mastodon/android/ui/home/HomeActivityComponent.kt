package com.sys1yagi.mastodon.android.ui.home

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(HomeActivityObjectModule::class))
interface HomeActivityComponent : AndroidInjector<HomeActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeActivity>() {

        abstract fun objectModule(objectModule: HomeActivityObjectModule)

        override fun seedInstance(instance: HomeActivity) {
            objectModule(HomeActivityObjectModule(instance))
        }
    }
}
