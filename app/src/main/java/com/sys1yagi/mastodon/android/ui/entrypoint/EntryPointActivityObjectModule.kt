package com.sys1yagi.mastodon.android.ui.entrypoint

import android.app.Activity
import dagger.Module
import dagger.Provides

@Module
class EntryPointActivityObjectModule(val view: EntryPointContract.View) {
    @Provides
    fun providePresenter(interactor: EntryPointInteractor, router: EntryPointRouter): EntryPointContract.Presenter {
        return EntryPointPresenter(view as Activity, view, interactor, router)
    }
}
