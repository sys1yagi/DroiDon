package com.sys1yagi.mastodon.android.ui.entrypoint

import android.app.Activity
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxTimelines
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EntryPointActivityObjectModule(val view: EntryPointContract.View) {
    @Provides
    fun providePresenter(interactor: EntryPointInteractor, router: EntryPointRouter): EntryPointContract.Presenter {
        return EntryPointPresenter(view as Activity, view, interactor, router)
    }

}
