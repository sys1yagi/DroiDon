package com.sys1yagi.mastodon.android.di

import com.google.gson.Gson
import com.sys1yagi.mastodon.android.DroiDonApplication
import com.sys1yagi.mastodon.android.ui.auth.login.LoginActivityModule
import com.sys1yagi.mastodon.android.ui.auth.setinstancename.SetInstanceNameModule
import com.sys1yagi.mastodon.android.ui.navigation.NavigationActivityModule
import com.sys1yagi.mastodon.android.ui.navigation.home.toot.TootActivityModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        OkHttpModule::class,
        StethoModule::class,
        NavigationActivityModule::class,
        LoginActivityModule::class,
        TootActivityModule::class
))
interface AppComponent {
    fun inject(target: DroiDonApplication)

    fun provideGson(): Gson
}
