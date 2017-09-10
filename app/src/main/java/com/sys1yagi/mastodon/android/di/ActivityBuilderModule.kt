package com.sys1yagi.mastodon.android.di

import com.sys1yagi.mastodon.android.ui.auth.setinstancename.SetInstanceNameActivity
import com.sys1yagi.mastodon.android.ui.auth.setinstancename.SetInstanceNameModule
import com.sys1yagi.mastodon.android.ui.entrypoint.EntryPointActivity
import com.sys1yagi.mastodon.android.ui.entrypoint.EntryPointModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(EntryPointModule::class))
    abstract fun entryPointActivity(): EntryPointActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(SetInstanceNameModule::class))
    abstract fun setInstanceNameActivity(): SetInstanceNameActivity

}
