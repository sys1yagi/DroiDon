package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon.android.di.FragmentScope
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.rx.RxPublic
import com.sys1yagi.mastodon4j.rx.RxStatuses
import com.sys1yagi.mastodon4j.rx.RxTimelines
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Named

@FragmentScope
@Module
class TimelineFragmentObjectModule(val view: TimelineContract.View, val type: StatusFetcher.Type) {
    @Provides
    fun providePresenter(@Named("instanceName") instanceName: String, interactor: TimelineInteractor, router: TimelineRouter): TimelineContract.Presenter {
        return TimelinePresenter(view as Fragment, view, instanceName, interactor, router)
    }

    @Provides
    fun provideStatusFetcher(rxTimelines: RxTimelines, rxPublic: RxPublic): StatusFetcher {
        return when (type) {
            StatusFetcher.Type.Home -> HomeStatusFetcher(rxTimelines)
            StatusFetcher.Type.LocalPublic -> LocalPublicStatusFetcher(rxPublic)
            StatusFetcher.Type.FederatedPublic -> FederatedPublicStatusFetcher(rxPublic)
            else -> HomeStatusFetcher(rxTimelines)
        }
    }

    // TODO move to the same scope as MastodonClient
    @FragmentScope
    @Provides
    fun provideRxTimelins(client: MastodonClient) = RxTimelines(client)

    @FragmentScope
    @Provides
    fun provideRxPublic(client: MastodonClient) = RxPublic(client)

    @FragmentScope
    @Provides
    fun provideRxStatuses(client: MastodonClient) = RxStatuses(client)

    @Named("instanceName")
    @Provides
    fun provideInstanceName(client: MastodonClient) = client.getInstanceName()
}
