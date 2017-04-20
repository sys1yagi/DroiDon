package com.sys1yagi.mastodon.android.ui.navigation.trip

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(TripFragmentObjectModule::class))
interface TripFragmentComponent : AndroidInjector<TripFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TripFragment>() {

        abstract fun objectModule(objectModule: TripFragmentObjectModule)

        override fun seedInstance(instance: TripFragment) {
            objectModule(TripFragmentObjectModule(instance))
        }
    }
}
