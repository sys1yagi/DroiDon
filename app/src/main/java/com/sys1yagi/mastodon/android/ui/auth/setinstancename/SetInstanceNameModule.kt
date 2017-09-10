package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import dagger.Module
import dagger.Provides

@Module
class SetInstanceNameModule {
    @Provides
    fun provideEntryPointPresenter(interactor: SetInstanceNameInteractor): SetInstanceNameContract.Presenter = SetInstanceNamePresenter(interactor)
}
