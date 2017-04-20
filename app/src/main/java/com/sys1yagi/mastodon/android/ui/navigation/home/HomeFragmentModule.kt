package com.sys1yagi.mastodon.android.ui.navigation.home

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(HomeFragmentComponent::class))
interface HomeFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    abstract fun bindLocalHomeInjectorFactory(builder: HomeFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}
