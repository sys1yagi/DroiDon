package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxTimelines
import dagger.Module
import dagger.Provides

@Module
class TimelineFragmentObjectModule(val view: TimelineContract.View) {
    @Provides
    fun providePresenter(interactor: TimelineInteractor, router: TimelineRouter): TimelineContract.Presenter {
        return TimelinePresenter(view as Fragment, view, interactor, router)
    }

    @Provides
    fun provideRxTimeline(client: MastodonClient) = RxTimelines(client)
}
