package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon.android.di.FragmentScope
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.method.Public
import com.sys1yagi.mastodon4j.api.method.Statuses
import com.sys1yagi.mastodon4j.api.method.Timelines
import dagger.Module
import dagger.Provides
import javax.inject.Named

@FragmentScope
@Module
class TimelineFragmentObjectModule(val view: TimelineContract.View, val type: StatusFetcher.Type) {
    @Provides
    fun providePresenter(@Named("instanceName") instanceName: String, interactor: TimelineInteractor, router: TimelineRouter): TimelineContract.Presenter {
        return TimelinePresenter(view as Fragment, view, instanceName, interactor, router)
    }

    @Provides
    fun provideStatusFetcher(timelines: Timelines, public: Public): StatusFetcher {
        return when (type) {
            StatusFetcher.Type.Home -> HomeStatusFetcher(timelines)
            StatusFetcher.Type.LocalPublic -> LocalPublicStatusFetcher(public)
            StatusFetcher.Type.FederatedPublic -> FederatedPublicStatusFetcher(public)
            else -> HomeStatusFetcher(timelines)
        }
    }

    // TODO move to the same scope as MastodonClient
    @FragmentScope
    @Provides
    fun provideStatuses(client: MastodonClient) = Statuses(client)

    @FragmentScope
    @Provides
    fun providePublic(client: MastodonClient) = Public(client)

    @FragmentScope
    @Provides
    fun provideTimelines(client: MastodonClient) = Timelines(client)

    @Named("instanceName")
    @Provides
    fun provideInstanceName(client: MastodonClient) = client.getInstanceName()
}
