package com.sys1yagi.mastodon.android.ui.auth.login

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(LoginActivityObjectModule::class))
interface LoginActivityComponent : AndroidInjector<LoginActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<LoginActivity>() {

        abstract fun objectModule(objectModule: LoginActivityObjectModule)

        override fun seedInstance(instance: LoginActivity) {
            objectModule(LoginActivityObjectModule(instance))
        }
    }
}
