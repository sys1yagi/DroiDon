package com.sys1yagi.mastodon.android.ui.entrypoint

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(EntryPointActivityObjectModule::class))
interface EntryPointActivityComponent : AndroidInjector<EntryPointActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<EntryPointActivity>() {

        abstract fun objectModule(objectModule: EntryPointActivityObjectModule)

        override fun seedInstance(instance: EntryPointActivity) {
            objectModule(EntryPointActivityObjectModule(instance))
        }
    }
}
