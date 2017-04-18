package com.sys1yagi.mastodon.android.ui.auth.login

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(LoginActivityComponent::class))
abstract class LoginActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(LoginActivity::class)
    abstract fun bindLoginActivityInjectorFactory(builder: LoginActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}
