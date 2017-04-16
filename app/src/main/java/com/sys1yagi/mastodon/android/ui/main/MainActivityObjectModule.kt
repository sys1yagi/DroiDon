package com.sys1yagi.mastodon.android.ui.main

import dagger.Module
import dagger.Provides

@Module
class MainActivityObjectModule(val view: MainContract.View) {
    @Provides
    fun providePresenter(interactor: MainInteractor): MainContract.Presenter {
        return MainPresenter(view, interactor)
    }
}
