package com.sys1yagi.mastodon.android.ui.navigation.settings

import dagger.Module
import dagger.Provides

@Module
class SettingsFragmentObjectModule {

    @Provides
    fun provideSettingsInitializer(): SettingsInitializer = DebugSettingsInitializerImpl()
}
