package com.sys1yagi.mastodon.android.ui.navigation.settings

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(SettingsFragmentObjectModule::class))
interface SettingsFragmentComponent : AndroidInjector<SettingsFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SettingsFragment>() {

        abstract fun objectModule(objectModule: SettingsFragmentObjectModule)

        override fun seedInstance(instance: SettingsFragment) {
            objectModule(SettingsFragmentObjectModule(instance.instanceName, instance))
        }
    }
}
