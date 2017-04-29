package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxTimelines
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class TimelineFragmentObjectModule(val view: TimelineContract.View, val type: StatusFetcher.Type) {
    @Provides
    fun providePresenter(@Named("instanceName") instanceName: String, interactor: TimelineInteractor, router: TimelineRouter): TimelineContract.Presenter {
        return TimelinePresenter(view as Fragment, view, instanceName, interactor, router)
    }

    @Provides
    fun provideStatusFetcher(client: MastodonClient): StatusFetcher {
        return when (type) {
            StatusFetcher.Type.Home -> HomeStatusFetcher(RxTimelines(client))
            StatusFetcher.Type.LocalPublic -> LocalPublicStatusFetcher(RxTimelines(client))
            StatusFetcher.Type.FederatedPublic -> FederatedPublicStatusFetcher(RxTimelines(client))
            else -> HomeStatusFetcher(RxTimelines(client))
        }
    }

    @Named("instanceName")
    @Provides
    fun provideInstanceName(client: MastodonClient) = client.getInstanceName()
}
