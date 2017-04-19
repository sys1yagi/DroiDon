package com.sys1yagi.mastodon.android.ui.home.localtimeline

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxTimelines
import dagger.Module
import dagger.Provides

@Module
class LocalTimelineFragmentObjectModule(val view: LocalTimelineContract.View) {
    @Provides
    fun providePresenter(interactor: LocalTimelineInteractor, router: LocalTimelineRouter): LocalTimelineContract.Presenter {
        return LocalTimelinePresenter(view as Fragment, view, interactor, router)
    }

    @Provides
    fun provideRxTimeline(client: MastodonClient) = RxTimelines(client)
}
