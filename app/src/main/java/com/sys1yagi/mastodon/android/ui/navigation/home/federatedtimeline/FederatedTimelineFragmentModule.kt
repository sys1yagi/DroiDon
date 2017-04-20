package com.sys1yagi.mastodon.android.ui.navigation.home.federatedtimeline

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(FederatedTimelineFragmentComponent::class))
interface FederatedTimelineFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(FederatedTimelineFragment::class)
    abstract fun bindLocalFederatedTimelineInjectorFactory(builder: FederatedTimelineFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}
