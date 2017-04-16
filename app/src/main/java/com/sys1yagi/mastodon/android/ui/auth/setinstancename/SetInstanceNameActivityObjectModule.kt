package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import dagger.Module
import dagger.Provides

@Module
class SetInstanceNameActivityObjectModule(val view: SetInstanceNameContract.View) {
    @Provides
    fun providePresenter(interactor: SetInstanceNameInteractor): SetInstanceNameContract.Presenter {
        return SetInstanceNamePresenter(view, interactor)
    }
}
