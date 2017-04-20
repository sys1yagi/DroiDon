package com.sys1yagi.mastodon.android.ui.navigation.trip

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(TripFragmentComponent::class))
interface TripFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(TripFragment::class)
    abstract fun bindLocalTripInjectorFactory(builder: TripFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}
