package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(SetInstanceNameActivityObjectModule::class))
interface SetInstanceNameActivityComponent : AndroidInjector<SetInstanceNameActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SetInstanceNameActivity>() {

        abstract fun objectModule(objectModule: SetInstanceNameActivityObjectModule)

        override fun seedInstance(instance: SetInstanceNameActivity) {
            objectModule(SetInstanceNameActivityObjectModule(instance))
        }
    }
}
