package com.sys1yagi.mastodon.android.di

import com.sys1yagi.mastodon.android.MastodonAndroidApplication
import com.sys1yagi.mastodon.android.di.module.AppModule
import com.sys1yagi.mastodon.android.ui.main.MainActivityModule
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class
))
interface AppComponent {

    fun inject(target: MastodonAndroidApplication)
}
