package com.sys1yagi.mastodon.android.di

import com.sys1yagi.mastodon.android.MastodonAndroidApplication
import com.sys1yagi.mastodon.android.ui.auth.setinstancename.SetInstanceNameActivityModule
import com.sys1yagi.mastodon.android.ui.entrypoint.EntryPointActivityModule
import com.sys1yagi.mastodon.android.ui.login.LoginActivityModule
import com.sys1yagi.mastodon.android.ui.main.MainActivityModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        EntryPointActivityModule::class,
        MainActivityModule::class,
        SetInstanceNameActivityModule::class,
        LoginActivityModule::class
))
interface AppComponent {

    fun inject(target: MastodonAndroidApplication)
}
