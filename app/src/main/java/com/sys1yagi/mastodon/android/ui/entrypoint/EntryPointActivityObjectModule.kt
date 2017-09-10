package com.sys1yagi.mastodon.android.ui.entrypoint

import dagger.Module
import dagger.Provides

@Module
class EntryPointActivityObjectModule(val view: EntryPointContract.View) {
    @Provides
    fun providePresenter(interactor: EntryPointInteractor): EntryPointContract.Presenter {
        return EntryPointPresenter(interactor)
    }
}
