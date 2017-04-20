package com.sys1yagi.mastodon.android.ui.navigation.home.federatedtimeline

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(FederatedTimelineFragmentObjectModule::class))
interface FederatedTimelineFragmentComponent : AndroidInjector<FederatedTimelineFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<FederatedTimelineFragment>() {

        abstract fun objectModule(objectModule: FederatedTimelineFragmentObjectModule)

        override fun seedInstance(instance: FederatedTimelineFragment) {
            objectModule(FederatedTimelineFragmentObjectModule(instance))
        }
    }
}
