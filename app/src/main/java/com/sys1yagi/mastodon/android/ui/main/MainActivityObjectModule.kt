package com.sys1yagi.mastodon.android.ui.main

import dagger.Module
import dagger.Provides

@Module
class MainActivityObjectModule {
    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }
}
