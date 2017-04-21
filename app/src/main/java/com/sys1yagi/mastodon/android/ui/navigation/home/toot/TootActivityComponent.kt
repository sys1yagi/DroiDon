package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(TootActivityObjectModule::class))
interface TootActivityComponent : AndroidInjector<TootActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TootActivity>() {

        abstract fun objectModule(objectModule: TootActivityObjectModule)

        override fun seedInstance(instance: TootActivity) {
            objectModule(TootActivityObjectModule(instance.primaryInstanceName, instance))
        }
    }
}
