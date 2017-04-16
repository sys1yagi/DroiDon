package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import android.app.Activity
import dagger.Module
import dagger.Provides

@Module
class SetInstanceNameActivityObjectModule(val view: SetInstanceNameContract.View) {
    @Provides
    fun providePresenter(interactor: SetInstanceNameInteractor, router: SetInstanceNameRouter): SetInstanceNameContract.Presenter {
        return SetInstanceNamePresenter(view as Activity, view, interactor, router)
    }
}
