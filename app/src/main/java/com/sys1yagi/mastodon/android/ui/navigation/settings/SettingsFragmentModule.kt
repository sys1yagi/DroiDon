package com.sys1yagi.mastodon.android.ui.navigation.settings

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(SettingsFragmentComponent::class))
interface SettingsFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(SettingsFragment::class)
    abstract fun bindLocalTripInjectorFactory(builder: SettingsFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}
