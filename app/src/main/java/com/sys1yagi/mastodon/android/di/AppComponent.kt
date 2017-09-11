package com.sys1yagi.mastodon.android.di

import com.sys1yagi.mastodon.android.DroiDonApplication
import com.sys1yagi.mastodon.android.ui.auth.login.LoginActivityModule
import com.sys1yagi.mastodon.android.ui.navigation.NavigationActivityModule
import com.sys1yagi.mastodon.android.ui.navigation.home.toot.TootActivityModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
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
interface AppComponent : AndroidInjector<DroiDonApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DroiDonApplication>() {
        abstract fun appModule(appModule: AppModule)
        abstract fun stethoModule(stethoModule: StethoModule)
        override fun seedInstance(instance: DroiDonApplication) {
            appModule(AppModule(instance))
            stethoModule(StethoModule(instance))
        }
    }
}
