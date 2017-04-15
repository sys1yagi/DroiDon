package com.sys1yagi.mastodon.android.ui.main

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxTimelines
import dagger.Module
import dagger.Provides

@Module
class MainActivityObjectModule(val view: MainContract.View) {
    @Provides
    fun providePresenter(interactor: MainContract.Interactor): MainContract.Presenter {
        return MainPresenter(view, interactor)
    }

    @Provides
    fun provideInteractor(client: MastodonClient): MainContract.Interactor {
        return MainInteractor(RxTimelines(client))
    }
}
