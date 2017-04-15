package com.sys1yagi.mastodon.android.ui.main

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(MainActivityObjectModule::class))
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>() {

        abstract fun objectModule(objectModule: MainActivityObjectModule)

        override fun seedInstance(instance: MainActivity) {
            objectModule(MainActivityObjectModule(instance))
        }
    }
}
