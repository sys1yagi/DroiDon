package com.sys1yagi.mastodon.android.ui.entrypoint

import dagger.Module
import dagger.Provides

@Module
class EntryPointModule {
    @Provides
    fun provideEntryPointPresenter(interactor: EntryPointInteractor): EntryPointContract.Presenter = EntryPointPresenter(interactor)
}
