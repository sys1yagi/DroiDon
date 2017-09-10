package com.sys1yagi.mastodon.android.di

import com.sys1yagi.mastodon.android.ui.entrypoint.EntryPointActivity
import com.sys1yagi.mastodon.android.ui.entrypoint.EntryPointModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = arrayOf(EntryPointModule::class))
    abstract fun entryPointActivity(): EntryPointActivity
}
